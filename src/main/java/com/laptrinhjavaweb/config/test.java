public class FirstAccessInterceptor extends HandlerInterceptorAdapter {

    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstAccessInterceptor.class);

    private String changePasswordUrl;
    private MessageSourceAccessor messages;
    private UserManager userManager;

    @Autowired
    public void setMessages(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public String getChangePasswordUrl() {
        return changePasswordUrl;
    }

    public void setChangePasswordUrl(String changePasswordUrl) {
        this.changePasswordUrl = changePasswordUrl;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
        //request.setAttribute("startTime", startTime);
        // if returned false, we need to make sure 'response' is sent

        User currentUser = (User) UserContext.getUserDetails();
        if (currentUser != null) {
            //LOGGER.info("PATH-INFO: " + request.getPathInfo());
            if (isFirstTimeLogin(currentUser.getUsername())) {
                if (!request.getPathInfo().equals(getChangePasswordUrl())) { // avoid redirection infinite loop!
                    saveError(request, getText("errors.login.change.password", Locale.getDefault()));
                    redirect(request, response, getChangePasswordUrl());
                    return false; // request handled, no need to bother controller
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //LOGGER.info("Request URL::" + request.getRequestURL().toString() + " Sent to Handler :: Current Time=" + System.currentTimeMillis());
        // we can add attributes in the modelAndView and use that in the view page
    }


    @Override
    public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex)	throws Exception {
        //long startTime = (Long) request.getAttribute("startTime");
        //LOGGER.info("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
        //LOGGER.info("Request URL::" + request.getRequestURL().toString() + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }

    /**
     * Checks if is first time login.
     *
     * @return true, if is first time login
     */
    private boolean isFirstTimeLogin(String username) {
        User user = (User) userManager.loadUserByUsername(username);
        //LOGGER.info("Password Change Date: " + user.getPasswordChangedDate());
        if (user.getPasswordChangedDate() == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Redirect.
     *
     * @param request
     *            the request
     * @param response
     *            the response
     * @param path
     *            the path
     * @throws ServletException
     *             the servlet exception
     */
    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (java.io.IOException e) {
            throw new ServletException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void saveError(HttpServletRequest request, String error) {
        List errors = (List) request.getSession().getAttribute("errors");
        if (errors == null) {
            errors = new ArrayList();
        }
        if (!errors.contains(error)) {
            errors.add(error);
        }
        request.getSession().setAttribute("errors", errors);
    }

    /**
     * Convenience method for getting a i18n key's value. Calling
     * getMessageSourceAccessor() is used because the RequestContext variable is
     * not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale
     *            the current locale
     * @return
     */
    public String getText(String msgKey, Locale locale) {
        return messages.getMessage(msgKey, locale);
    }
}

