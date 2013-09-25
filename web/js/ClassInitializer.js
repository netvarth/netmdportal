var pageHandler = new PageHandler();
var constants = new Constants();
var ajaxProcessor = new ServerUrlProcessor();
var dataTableProcessor = new DataTableProcessor();
var commonMethodInvoker = new CommonMethodInvoker();
var validator = new Validator();
var methodInvoker = new WeblimsMethodInvoker();
var weblimsResultInvoker = new WeblimsResultInvoker();
var cookieProcessor = new CookieProcessor();
var printSetupProcessor = new PrintAppletProcessor(cookieProcessor);