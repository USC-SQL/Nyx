

if (Function.prototype.bind == null) {
Function.prototype.bind = function(object) {
var __method = this;
return function() {
return __method.apply(object, arguments);
}
}
}

if (typeof(Wicket) == "undefined")
Wicket = { };
Wicket.$ = function(arg) {
if (arg == null || typeof(arg) == "undefined") {
return null;
}
if (arguments.length > 1) {
var e=[];
for (var i=0; i<arguments.length; i++) {
e.push(Wicket.$(arguments[i]));
}
return e;
} else if (typeof arg == 'string') {
return document.getElementById(arg);
} else {
return arg;
}
}
Wicket.$$ = function(element) {
if (typeof(element) == "string") {
element = Wicket.$(element);
}
if (element == null || typeof(element) == "undefined" ||
element.tagName == null || typeof(element.tagName) == "undefined") {
return true;
}
var id = element.getAttribute('id');
if (typeof(id) == "undefined" || id == null || id == "")
return element.ownerDocument == document;
else
return document.getElementById(id) == element;
}
Wicket.isPortlet = function() {
return Wicket.portlet == true;
}
Wicket.emptyFunction = function() { };
Wicket.Class = {
create: function() {
return function() {
this.initialize.apply(this, arguments);
}
}
}

if (typeof DOMParser == "undefined" && Wicket.Browser.isSafari()) {
DOMParser = function () {}
DOMParser.prototype.parseFromString = function (str, contentType) {
alert('You are using an old version of Safari.\nTo be able to use this page you need at least version 2.0.1.');
}
}

Wicket.Log = {
enabled: function() {
return wicketAjaxDebugEnabled();
},
info: function(msg) {
if (Wicket.Log.enabled())
WicketAjaxDebug.logInfo(msg);
},
error: function(msg) {
if (Wicket.Log.enabled())
WicketAjaxDebug.logError(msg);
},
log: function(msg) {
if(Wicket.Log.enabled())
WicketAjaxDebug.log(msg);
}
},

Wicket.FunctionsExecuter = Wicket.Class.create();
Wicket.FunctionsExecuter.prototype = {
initialize: function(functions) {
this.functions = functions;
this.current = 0;
this.depth = 0; 	},
processNext: function() {
if (this.current < this.functions.length) {
var f = this.functions[this.current];
var run = function() {
f(this.notify.bind(this));
}.bind(this);
this.current++;
if (this.depth > 50 || Wicket.Browser.isKHTML() || Wicket.Browser.isSafari()) {


this.depth = 0;
window.setTimeout(run, 1);
} else {
this.depth ++;
run();
}
}
},
start: function() {
this.processNext();
},
notify: function() {
this.processNext();
}
}
Wicket.replaceOuterHtmlIE = function(element, text) {
	var marker = "__WICKET_JS_REMOVE_X9F4A__";
function markIframe(text) {
var t = text;
var r = /<\s*iframe/i;
while ((m = t.match(r)) != null) {
t = Wicket.replaceAll(t, m[0], "<" + marker + m[0].substring(1));
}
return t;
}
function removeIframeMark(text) {
return Wicket.replaceAll(text, marker, "");
}
if (element.tagName == "SCRIPT") {


var tempDiv = document.createElement("div");
tempDiv.innerHTML = "<table>" + text + "</table>";
var script = tempDiv.childNodes[0].childNodes[0].innerHTML;
element.outerHtml = text;
eval(script);
return;
}
var parent = element.parentNode;
var tn = element.tagName;
var tempDiv = document.createElement("div");
var tempParent;
	var scripts = new Array();
if (window.parent == window || window.parent == null) {
document.body.appendChild(tempDiv);
}
if (tn != 'TBODY' && tn != 'TR' && tn != "TD" && tn != "THEAD" && tn != "TFOOT" && tn != "TH") {



tempDiv.innerHTML = '<table style="display:none">' + markIframe(text) + '</table>';

var s = tempDiv.getElementsByTagName("script");
for (var i = 0; i < s.length; ++i) {
scripts.push(s[i]);
}

tempDiv.innerHTML = '<div style="display:none">' + text + '</div>';

tempParent = tempDiv.childNodes[0];
tempParent.parentNode.removeChild(tempParent);
} else {


tempDiv.innerHTML = '<div style="display:none">' + markIframe(text) + '</div>';

var s = tempDiv.getElementsByTagName("script");
for (var i = 0; i < s.length; ++i) {
scripts.push(s[i]);
}

tempDiv.innerHTML = '<table style="display: none">' + text + '</table>';

tempParent = tempDiv.getElementsByTagName(tn).item(0).parentNode;
}
	while(tempParent.childNodes.length > 0) {
var tempElement = tempParent.childNodes[0];
tempParent.removeChild(tempElement);
parent.insertBefore(tempElement, element);
tempElement = null;
}
	parent.removeChild(element);
element.outerHTML = "";
element = "";
if (window.parent == window || window.parent == null) {
document.body.removeChild(tempDiv);
}
tempDiv.outerHTML = "";
parent = null;
tempDiv = null;
tempParent = null;
for (i = 0; i < scripts.length; ++i) {
Wicket.Head.addJavascripts(scripts[i], removeIframeMark);
}
}
Wicket.replaceOuterHtmlSafari = function(element, text) {
	if (element.tagName == "SCRIPT") {

var tempDiv = document.createElement("div");
tempDiv.innerHTML = text;

var script = tempDiv.childNodes[0].innerHTML;
if (typeof(script) != "string") {
script = tempDiv.childNodes[0].text;
}
element.outerHTML = text;
eval(script);
return;
}
var parent = element.parentNode;
var next = element.nextSibling;
var index = 0;
while (parent.childNodes[index] != element) {
++index;
}
element.outerHTML = text;
element = parent.childNodes[index];
		while (element != next) {
Wicket.Head.addJavascripts(element);
element = element.nextSibling;
}
}

Wicket.replaceOuterHtml = function(element, text) {
if (Wicket.Browser.isIE()) {
Wicket.replaceOuterHtmlIE(element, text);
} else if (Wicket.Browser.isSafari() || Wicket.Browser.isOpera()) {
Wicket.replaceOuterHtmlSafari(element, text);
} else  {

var range = element.ownerDocument.createRange();
range.selectNode(element);
var fragment = range.createContextualFragment(text);
element.parentNode.replaceChild(fragment, element);
}
}

Wicket.decode = function(encoding, text) {
if (encoding == "wicket1") {
return Wicket.decode1(text);
}
}
Wicket.decode1 = function(text) {
return Wicket.replaceAll(text, "]^", "]");
}
Wicket.replaceAll = function(str, from, to) {
var idx = str.indexOf(from);
while (idx > -1) {
str = str.replace(from, to);
idx = str.indexOf(from);
}
return str;
}

Wicket.Form = { }
Wicket.Form.encode = function(text) {
if (encodeURIComponent) {
return encodeURIComponent(text);
} else {
return escape(text);
}
}
Wicket.Form.serializeSelect = function(select){
	if (select.multiple == false){
return Wicket.Form.encode(select.name) + "=" + Wicket.Form.encode(select.value) + "&";
}

var result = "";
for (var i = 0; i < select.options.length; ++i) {
var option = select.options[i];
if (option.selected) {
result += Wicket.Form.encode(select.name) + "=" + Wicket.Form.encode(option.value) + "&";
}
}
return result;
}
Wicket.Form.serializeInput = function(input) {
var type = input.type.toLowerCase();
if ((type == "checkbox" || type == "radio") && input.checked) {
return Wicket.Form.encode(input.name) + "=" + Wicket.Form.encode(input.value) + "&";
} else if (type == "text" || type == "password" || type == "hidden" || type == "textarea" || type == "search") {
return Wicket.Form.encode(input.name) + "=" + Wicket.Form.encode(input.value) + "&";
} else {
return "";
}
}
Wicket.Form.excludeFromAjaxSerialization = {};
Wicket.Form.serializeElement = function(e) {
if (Wicket.Form.excludeFromAjaxSerialization && e.id && Wicket.Form.excludeFromAjaxSerialization[e.id] == "true") {
return "";
}
var tag = e.tagName.toLowerCase();
if (tag == "select") {
return Wicket.Form.serializeSelect(e);
} else if (tag == "input" || tag == "textarea") {
return Wicket.Form.serializeInput(e);
} else {
return "";
}
}
Wicket.Form.doSerialize = function(form) {
var result = "";
for (var i = 0; i < form.elements.length; ++i) {
var e = form.elements[i];
if (e.name && e.name != "" && !e.disabled) {
result += Wicket.Form.serializeElement(e);
}
}
return result;
}
Wicket.Form.serialize = function(element, dontTryToFindRootForm) {
if (element.tagName.toLowerCase() == "form") {
return Wicket.Form.doSerialize(element);
} else {

var elementBck = element;
if (dontTryToFindRootForm != true) {
do {
element = element.parentNode;
} while(element.tagName.toLowerCase() != "form" && element.tagName.toLowerCase() != "body")
}
if (element.tagName.toLowerCase() == "form"){

return Wicket.Form.doSerialize(element);
} else {


var form = document.createElement("form");
parent = elementBck.parentNode;
parent.replaceChild(form, elementBck);
form.appendChild(elementBck);
var result = Wicket.Form.doSerialize(form);
parent.replaceChild(elementBck, form);
return result
}
}
}

Wicket.DOM = { }
Wicket.DOM.serializeNodeChildren = function(node) {
if (node == null) {
return ""
}
var result = "";
for (var i = 0; i < node.childNodes.length; i++) {
var thisNode = node.childNodes[i];
switch (thisNode.nodeType) {
case 1: 
case 5: 
result += Wicket.DOM.serializeNode(thisNode);
break;
case 8: 
result += "<!--" + thisNode.nodeValue + "-->";
break;
case 4: 
result += "<![CDATA[" + thisNode.nodeValue + "]]>";
break;
case 3: 
case 2: 
result += thisNode.nodeValue;
break;
default:
break;
}
}
return result;
}
Wicket.DOM.serializeNode = function(node){
if (node == null) {
return ""
}
var result = "";
result += '<' + node.nodeName;
if (node.attributes && node.attributes.length > 0) {
for (var i = 0; i < node.attributes.length; i++) {
result += " " + node.attributes[i].name
+ "=\"" + node.attributes[i].value + "\"";
}
}
result += '>';
result += Wicket.DOM.serializeNodeChildren(node);
result += '</' + node.nodeName + '>';
return result;
}
Wicket.DOM.containsElement = function(element) {
var id = element.getAttribute("id");
if (id != null)
return Wicket.$(id) != null;
else
return false;
}

Wicket.Channel = Wicket.Class.create();
Wicket.Channel.prototype = {
initialize: function(name) {
var res = name.match(/^([^|]+)\|(d|s)$/)
if (res == null)
this.type ='s'; 
else
this.type = res[2];
this.callbacks = new Array();
this.busy = false;
},
schedule: function(callback) {
if (this.busy == false) {
this.busy = true;
return callback();
} else {
Wicket.Log.info("Channel busy - postponing...");
if (this.type == 's') 
this.callbacks.push(callback);
else 
this.callbacks[0] = callback;
return null;
}
},
done: function() {
var c = null;
if (this.callbacks.length > 0) {
c = this.callbacks.shift();
}
if (c != null && typeof(c) != "undefined") {
Wicket.Log.info("Calling posponed function...");


window.setTimeout(c, 1);
} else {
this.busy = false;
}
}
};

Wicket.ChannelManager = Wicket.Class.create();
Wicket.ChannelManager.prototype = {
initialize: function() {
this.channels = new Array();
},
	schedule: function(channel, callback) {
var c = this.channels[channel];
if (c == null) {
c = new Wicket.Channel(channel);
this.channels[channel] = c;
}
return c.schedule(callback);
},
		done: function(channel) {
var c = this.channels[channel];
if (c != null)
c.done();
}
};
Wicket.channelManager = new Wicket.ChannelManager();

Wicket.Ajax = {
	createTransport: function() {
var transport = null;
if (window.ActiveXObject) {
transport = new ActiveXObject("Microsoft.XMLHTTP");
} else if (window.XMLHttpRequest) {
transport = new XMLHttpRequest();
}
if (transport == null) {
Wicket.Log.error("Could not locate ajax transport. Your browser does not support the required XMLHttpRequest object or wicket could not gain access to it.");
}
return transport;
},
transports: [],
	getTransport: function() {
var t = Wicket.Ajax.transports;
for (var i = 0; i < t.length; ++i) {
if (t[i].readyState == 0) {
return t[i];
}
}
t.push(Wicket.Ajax.createTransport());
return t[t.length-1];
},
preCallHandlers: [],
postCallHandlers: [],
failureHandlers: [],
registerPreCallHandler: function(handler) {
var h = Wicket.Ajax.preCallHandlers;
h.push(handler);
},
registerPostCallHandler: function(handler) {
var h = Wicket.Ajax.postCallHandlers;
h.push(handler);
},
registerFailureHandler: function(handler) {
var h = Wicket.Ajax.failureHandlers;
h.push(handler);
},
invokePreCallHandlers: function() {
var h = Wicket.Ajax.preCallHandlers;
if (h.length > 0) {
Wicket.Log.info("Invoking pre-call handler(s)...");
}
for (var i = 0; i < h.length; ++i) {
h[i]();
}
},
invokePostCallHandlers: function() {
var h = Wicket.Ajax.postCallHandlers;
if (h.length > 0) {
Wicket.Log.info("Invoking post-call handler(s)...");
}
for (var i = 0; i < h.length; ++i) {
h[i]();
}
},
invokeFailureHandlers: function() {
var h = Wicket.Ajax.failureHandlers;
if (h.length > 0) {
Wicket.Log.info("Invoking failure handler(s)...");
}
for (var i = 0; i < h.length; ++i) {
h[i]();
}
}
}

Wicket.Ajax.Request = Wicket.Class.create();
Wicket.Ajax.Request.prototype = {
	initialize: function(url, loadedCallback, parseResponse, randomURL, failureHandler, channel) {
this.url = url;
this.loadedCallback = loadedCallback;

this.parseResponse = parseResponse != null ? parseResponse : true;
this.randomURL = randomURL != null ? randomURL : true;
this.failureHandler = failureHandler != null ? failureHandler : function() { };
this.async = true;
this.channel = channel;
this.precondition = function() { return true; } 


this.suppressDone = false;
this.instance = Math.random();
this.debugContent = true;
},
done: function() {
Wicket.channelManager.done(this.channel);
},
createUrl: function() {
if (this.randomURL == false)
return this.url;
else
return this.url + (this.url.indexOf("?")>-1 ? "&" : "?") + "random=" + Math.random();
},
log: function(method, url) {
var log = Wicket.Log.info;
log("");
log("Initiating Ajax "+method+" request on " + url);
},
failure: function() {
this.failureHandler();
Wicket.Ajax.invokePostCallHandlers();
Wicket.Ajax.invokeFailureHandlers();
},
	get: function() {
if (Wicket.isPortlet()) {

var qs = this.url.indexOf('?');
if (qs==-1) {
qs = this.url.indexOf('&');
}
if (qs>-1) {
var query = this.url.substring(qs+1);

if (query && query.length > 0) {

this.url = this.url.substring(0,qs);

if (query.charAt(query.length-1)!='&') {
query += "&";
}


return this.post(query);
}
}
}
if (this.channel != null) {
var res = Wicket.channelManager.schedule(this.channel, this.doGet.bind(this));
return res != null ? res : true;
} else {
return this.doGet();
}
},
	doGet: function() {
if (this.precondition()) {
this.transport = Wicket.Ajax.getTransport();
var url = this.createUrl();
this.log("GET", url);
Wicket.Ajax.invokePreCallHandlers();
var t = this.transport;
if (t != null) {
t.open("GET", url, this.async);
t.onreadystatechange = this.stateChangeCallback.bind(this);

t.setRequestHeader("Wicket-Ajax", "true");
t.setRequestHeader("Wicket-FocusedElementId", Wicket.Focus.lastFocusId || "");
t.setRequestHeader("Accept", "text/xml");
t.send(null);
return true;
} else {
this.failure();
return false;
}
} else {
this.done();
return true;
}
},
	post: function(body) {
if (this.channel != null) {
var res = Wicket.channelManager.schedule(this.channel, function() { this.doPost(body); }.bind(this));
return res != null ? res: true;
} else {
return this.doPost(body);
}
},
	doPost: function(body) {
if (this.precondition()) {
this.transport = Wicket.Ajax.getTransport();
var url = this.createUrl();
this.log("POST", url);
Wicket.Ajax.invokePreCallHandlers();
var t = this.transport;
if (t != null) {

if (typeof(body) == "function") {
body = body();
}
t.open("POST", url, this.async);
t.onreadystatechange = this.stateChangeCallback.bind(this);
t.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

t.setRequestHeader("Wicket-Ajax", "true");
t.setRequestHeader("Wicket-FocusedElementId", Wicket.Focus.lastFocusId || "");
t.setRequestHeader("Accept", "text/xml");
t.send(body);
return true;
} else {
this.failure();
return false;
}
} else {
this.done();
return true;
}
},
	stateChangeCallback: function() {
var t = this.transport;
if (t != null && t.readyState == 4) {
try {
status = t.status;
}
catch (e) {
Wicket.Log.error("Exception evaluating AJAX status: " + e);
status = "unavailable";
}
if (status == 200 || status == "") { 

var responseAsText = t.responseText;

var redirectUrl;
try {
redirectUrl = t.getResponseHeader('Ajax-Location');
} catch (ignore) { 
}

if (typeof(redirectUrl) != "undefined" && redirectUrl != null && redirectUrl != "") {
t.onreadystatechange = Wicket.emptyFunction;

if (redirectUrl.charAt(0)==('/')||redirectUrl.match("^http://")=="http://"||redirectUrl.match("^https://")=="https://") {
window.location = redirectUrl;
}
else {
var urlDepth = 0;
while (redirectUrl.substring(0, 3) == "../") {
urlDepth++;
redirectUrl = redirectUrl.substring(3);
}

var calculatedRedirect = window.location.pathname;
while (urlDepth > -1) {
urlDepth--;
i = calculatedRedirect.lastIndexOf("/");
if (i > -1) {
calculatedRedirect = calculatedRedirect.substring(0, i);
}
}
calculatedRedirect += "/" + redirectUrl;
window.location = calculatedRedirect;
}
}
else {

var log = Wicket.Log.info;
log("Received ajax response (" + responseAsText.length + " characters)");
if (this.debugContent != false) {
log("\n" + responseAsText);
}

if (this.parseResponse == true) {
var xmldoc;
if (typeof(window.XMLHttpRequest) != "undefined" && typeof(DOMParser) != "undefined") {
var parser = new DOMParser();
xmldoc = parser.parseFromString(responseAsText, "text/xml");
} else if (window.ActiveXObject) {
xmldoc = t.responseXML;
}

this.loadedCallback(xmldoc);
} else {

this.loadedCallback(responseAsText);
}
if (this.suppressDone == false)
this.done();
}
} else {

var log = Wicket.Log.error;
log("Received Ajax response with code: " + status);
if (status == 500) {
log("500 error had text: " + t.responseText);
}
this.done();
this.failure();
}
t.onreadystatechange = Wicket.emptyFunction;
t.abort();
this.transport = null;
}
}
};

Wicket.Ajax.Call = Wicket.Class.create();
Wicket.Ajax.Call.prototype = {
	initialize: function(url, successHandler, failureHandler, channel) {
this.successHandler = successHandler != null ? successHandler : function() { };
this.failureHandler = failureHandler != null ? failureHandler : function() { };
var c = channel != null ? channel : "0|s"; 

this.request = new Wicket.Ajax.Request(url, this.loadedCallback.bind(this), true, true, failureHandler, c);
this.request.suppressDone = true;
},
	failure: function(message) {
if (message != null)
Wicket.Log.error("Error while parsing response: " + message);
this.request.done();
this.failureHandler();
Wicket.Ajax.invokePostCallHandlers();
Wicket.Ajax.invokeFailureHandlers();
},
	call: function() {
return this.request.get();
},
	post: function(body) {
return this.request.post(body);
},
		submitForm: function(form, submitButton) {
var body = function() {
var s = Wicket.Form.serialize(form);
if (submitButton != null) {
s += Wicket.Form.encode(submitButton) + "=1";
}
return s;
}
return this.request.post(body);
},
	submitFormById: function(formId, submitButton) {
var form = Wicket.$(formId);
if (form == null || typeof (form) == "undefined")
Wicket.Log.error("Trying to submit form with id '"+formId+"' that is not in document.");
return this.submitForm(form, submitButton);
},
	loadedCallback: function(envelope) {






try {
var root = envelope.getElementsByTagName("ajax-response")[0];

if (root == null || root.tagName != "ajax-response") {
this.failure("Could not find root <ajax-response> element");
return;
}

var steps = new Array();



steps.push(function(notify) {
window.setTimeout(notify,2);
}.bind(this));
if (Wicket.Browser.isKHTML()) {




steps.push = function(method) {
method(function() { });
}
}


for (var i = 0; i < root.childNodes.length; ++i) {
var node = root.childNodes[i];
if (node.tagName == "component") {
this.processComponent(steps, node);
} else if (node.tagName == "evaluate") {
this.processEvaluation(steps, node);
} else if (node.tagName == "header-contribution") {
this.processHeaderContribution(steps, node);
}
}

this.success(steps);
if (Wicket.Browser.isKHTML() == false) {
Wicket.Log.info("Response parsed. Now invoking steps...");
var executer = new Wicket.FunctionsExecuter(steps);
executer.start();
}
} catch (e) {
this.failure(e.message);
}
},
	success: function(steps) {
steps.push(function(notify) {
Wicket.Log.info("Response processed successfully.");
Wicket.Ajax.invokePostCallHandlers();



Wicket.Focus.attachFocusEvent();
this.request.done();
this.successHandler();

setTimeout("Wicket.Focus.requestFocus();", 0);

notify();
}.bind(this));
},
	processComponent: function(steps, node) {
steps.push(function(notify) {

var compId = node.getAttribute("id");
var text="";

if (node.hasChildNodes()) {
text = node.firstChild.nodeValue;
}


var encoding = node.getAttribute("encoding");
if (encoding != null && encoding!="") {
text = Wicket.decode(encoding, text);
}

var element = Wicket.$(compId);
if (element == null || typeof(element) == "undefined") {
Wicket.Log.error("Component with id [["+compId+"]] a was not found while trying to perform markup update. Make sure you called component.setOutputMarkupId(true) on the component whose markup you are trying to update.");
} else {

Wicket.replaceOuterHtml(element, text);
}

notify();
});
},
	processEvaluation: function(steps, node) {
steps.push(function(notify) {

var text = node.firstChild.nodeValue;

var encoding = node.getAttribute("encoding");
if (encoding != null) {
text = Wicket.decode(encoding, text);
}



var res = text.match(new RegExp("^([a-z|A-Z_][a-z|A-Z|0-9_]*)\\|((.|\\n)*)$"));
if (res != null) {
text = "var f = function(" + res[1] + ") {" + res[2] +"};";
try {

eval(text);
f(notify);
} catch (exception) {
Wicket.Log.error("Exception evaluating javascript: " + exception);
}
} else {

try {

eval(text);
} catch (exception) {
Wicket.Log.error("Exception evaluating javascript: " + exception);
}

notify();
}
});
},
	processHeaderContribution: function(steps, node) {
var c = new Wicket.Head.Contributor();
c.processContribution(steps, node);
}
};

Wicket.Head = { };
Wicket.Head.Contributor = Wicket.Class.create();
Wicket.Head.Contributor.prototype = {
initialize: function() {
},
	parse: function(headerNode) {






var text = headerNode.firstChild.nodeValue;
var encoding = headerNode.getAttribute("encoding");
if (encoding != null && encoding != "") {
text = Wicket.decode(encoding, text);
}
if (Wicket.Browser.isKHTML()) {

text = text.replace(/<script/g,"<SCRIPT");
text = text.replace(/<\/script>/g,"</SCRIPT>");
}

var xmldoc;
if (window.ActiveXObject) {
xmldoc = new ActiveXObject("Microsoft.XMLDOM");
xmldoc.loadXML(text);
} else {
var parser = new DOMParser();
xmldoc = parser.parseFromString(text, "text/xml");
}
return xmldoc;
},
	processContribution: function(steps, headerNode) {
var xmldoc = this.parse(headerNode);
var rootNode = xmldoc.documentElement;

for (var i = 0; i < rootNode.childNodes.length; i++) {
var node = rootNode.childNodes[i];
if (node.tagName != null) {
var name = node.tagName.toLowerCase();


if (name == "wicket:link") {
for (var j = 0; j < node.childNodes.length; ++j) {
var childNode = node.childNodes[j];

if (childNode.nodeType == 1) {
node = childNode;
name = node.tagName.toLowerCase();
break;
}
}
}

if (name == "link") {
this.processLink(steps, node);
} else if (name == "script") {
this.processScript(steps, node);
} else if (name == "style") {
this.processStyle(steps, node);
}
}
}
},
	processLink: function(steps, node) {
steps.push(function(notify) {

if (Wicket.Head.containsElement(node, "href")) {
notify();
return;
}

var css = Wicket.Head.createElement("link");

css.id = node.getAttribute("id");
css.rel = node.getAttribute("rel");
css.href = node.getAttribute("href");
css.type = node.getAttribute("type");

Wicket.Head.addElement(css);

notify();
});
},
	processStyle: function(steps, node) {
steps.push(function(notify) {

if (Wicket.DOM.containsElement(node)) {
notify();
return;
}

var content = Wicket.DOM.serializeNodeChildren(node);

var style = Wicket.Head.createElement("style");

style.id = node.getAttribute("id");

if (Wicket.Browser.isIE()) {
try
{
document.createStyleSheet().cssText = content;
}
catch(ignore)
{
var run = function() {
try
{
document.createStyleSheet().cssText = content;
}
catch(e)
{
Wicket.Log.error(e);
}
}
window.setTimeout(run, 1);
}
} else {
var textNode = document.createTextNode(content);
style.appendChild(textNode);
}
Wicket.Head.addElement(style);

notify();
});
},
	processScript: function(steps, node) {
steps.push(function(notify) {


if (Wicket.DOM.containsElement(node) ||
Wicket.Head.containsElement(node, "src")) {
notify();
return;
}

var src = node.getAttribute("src");
if (src != null && src != "") {


var onLoad = function(content) {
Wicket.Head.addJavascript(content, null, src);
Wicket.Ajax.invokePostCallHandlers();

notify();
}


window.setTimeout(function() {
var req = new Wicket.Ajax.Request(src, onLoad, false, false);
req.debugContent = false;
if (Wicket.Browser.isKHTML())


req.async = false;

req.get();
},1);
} else {

var text = Wicket.DOM.serializeNodeChildren(node);

Wicket.Head.addJavascript(text, node.getAttribute("id"));

notify();
}
});
}
};

Wicket.Head.createElement = function(name) {
return document.createElement(name);
}
Wicket.Head.addElement = function(element) {
var head = document.getElementsByTagName("head");
if (head[0]) {
head[0].appendChild(element);
}
}
Wicket.Head.containsElement = function(element, mandatoryAttribute) {
var attr = element.getAttribute(mandatoryAttribute);
if (attr == null || attr == "" || typeof(attr) == "undefined")
return false;
var head = document.getElementsByTagName("head")[0];
var nodes = head.getElementsByTagName(element.tagName);
for (var i = 0; i < nodes.length; ++i) {
var node = nodes[i];



if (node.tagName.toLowerCase() == element.tagName.toLowerCase() &&
(node.getAttribute(mandatoryAttribute) == attr ||
node.getAttribute(mandatoryAttribute+"_") == attr)) {
return true;
}
}
return false;
}
Wicket.Head.addJavascript = function(content, id, fakeSrc) {
var script = Wicket.Head.createElement("script");
script.id = id;
script.setAttribute("src_", fakeSrc);
	if (null == script.canHaveChildren || script.canHaveChildren) {
var textNode = document.createTextNode(content);
script.appendChild(textNode);
} else {
script.text = content;
}
Wicket.Head.addElement(script);
}
Wicket.Head.addJavascripts = function(element, contentFilter) {
function add(element) {
var src = element.getAttribute("src");

if (src != null && src.length > 0) {
var e = document.createElement("script");
e.setAttribute("type","text/javascript");
e.setAttribute("src", src);
Wicket.Head.addElement(e);
} else {
var content = Wicket.DOM.serializeNodeChildren(element);
if (content == null || content == "")
content = element.text;
if (typeof(contentFilter) == "function") {
content = contentFilter(content);
}
Wicket.Head.addJavascript(content);
}
}
if (typeof(element) != "undefined" &&
typeof(element.tagName) != "undefined" &&
element.tagName.toLowerCase() == "script") {
add(element);
} else {


if (element.childNodes.length > 0) {
var scripts = element.getElementsByTagName("script");
for (var i = 0; i < scripts.length; ++i) {
add(scripts[i]);
}
}
}
}

Wicket.ThrottlerEntry = Wicket.Class.create();
Wicket.ThrottlerEntry.prototype = {
initialize: function(func) {
this.func = func;
this.timestamp = new Date().getTime();
},
getTimestamp: function() {
return this.timestamp;
},
getFunc: function() {
return this.func;
},
setFunc: function(func) {
this.func = func;
}
};
Wicket.Throttler = Wicket.Class.create();
Wicket.Throttler.prototype = {
initialize: function() {
this.entries = new Array();
},
throttle: function(id, millis, func) {
var entry = this.entries[id];
var me = this;
if (entry == undefined) {
entry = new Wicket.ThrottlerEntry(func);
this.entries[id] = entry;
window.setTimeout(function() { me.execute(id); }, millis);
} else {
entry.setFunc(func);
}
},
execute: function(id) {
var entry = this.entries[id];
if (entry != undefined) {
var func = entry.getFunc();
var tmp = func();
}
this.entries[id] = undefined;
}
};
Wicket.throttler = new Wicket.Throttler();

Wicket.stopEvent = function(e) {
e=Wicket.fixEvent(e);
e.cancelBubble = true;
if (e.stopPropagation)
e.stopPropagation();
}

Wicket.fixEvent = function(e) {
if (typeof e == 'undefined')
e = window.event;
return e;
}

Wicket.Drag = {

init: function(element, onDragBegin, onDragEnd, onDrag) {
if (typeof(onDragBegin) == "undefined")
onDragBegin = Wicket.emptyFunction;
if (typeof(onDragEnd) == "undefined")
onDragEnd = Wicket.emptyFunction;
if (typeof(onDrag) == "undefined")
onDrag = Wicket.emptyFunction;
element.wicketOnDragBegin = onDragBegin;
element.wicketOnDrag = onDrag;
element.wicketOnDragEnd = onDragEnd;

Wicket.Event.add(element, "mousedown", Wicket.Drag.mouseDownHandler);
},
mouseDownHandler: function(e) {
e = Wicket.fixEvent(e);
var element = this;



if (typeof(e.ignore) == "undefined") {
Wicket.stopEvent(e);
element.wicketOnDragBegin(element);
element.lastMouseX = e.clientX;
element.lastMouseY = e.clientY;
element.old_onmousemove = document.onmousemove;
element.old_onmouseup = document.onmouseup;
element.old_onselectstart = document.onselectstart;
element.old_onmouseout = document.onmouseout;
document.onselectstart = function() { return false; }
document.onmousemove = Wicket.Drag.mouseMove;
document.onmouseup = Wicket.Drag.mouseUp;
document.onmouseout = Wicket.Drag.mouseOut;
Wicket.Drag.current = element;
return false;
}
},

clean: function(element) {
element.onmousedown = null;
},

mouseMove: function(e) {
e = Wicket.fixEvent(e);
var o = Wicket.Drag.current;

if (e.clientX < 0 || e.clientY < 0) {
return;
}
if (o != null) {
var deltaX = e.clientX - o.lastMouseX;
var deltaY = e.clientY - o.lastMouseY;
var res = o.wicketOnDrag(o, deltaX, deltaY, e);
if (res == null)
res = [0, 0];
o.lastMouseX = e.clientX + res[0];
o.lastMouseY = e.clientY + res[1];
}
return false;
},

mouseUp: function(e) {
e = Wicket.fixEvent(e);
var o = Wicket.Drag.current;
if (o != null && typeof(o) != "undefined") {
o.wicketOnDragEnd(o);
o.lastMouseX = null;
o.lastMouseY = null;
document.onmousemove = o.old_onmousemove;
document.onmouseup = o.old_onmouseup;
document.onselectstart = o.old_onselectstart;
document.onmouseout = o.old_onmouseout;
o.old_mousemove = null;
o.old_mouseup = null;
o.old_onselectstart = null;
o.old_onmouseout = null;
Wicket.Drag.current = null;
}
},

mouseOut: function(e) {
if (false && Wicket.Browser.isGecko()) {

e = Wicket.fixEvent(e);
if (e.target.tagName == "HTML") {
Wicket.Drag.mouseUp(e);
}
}
}
};
Wicket.ChangeHandler=function(elementId){
var KEY_BACKSPACE=8;
var KEY_TAB=9;
var KEY_ENTER=13;
var KEY_ESC=27;
var KEY_LEFT=37;
var KEY_UP=38;
var KEY_RIGHT=39;
var KEY_DOWN=40;
var KEY_SHIFT=16;
var KEY_CTRL=17;
var KEY_ALT=18;
var obj = Wicket.$(elementId);
obj.setAttribute("autocomplete", "off");
if (Wicket.Browser.isIE() || Wicket.Browser.isKHTML() || Wicket.Browser.isSafari()) {
var objonchange = obj.onchange;
obj.onkeyup = function(event) {
switch (wicketKeyCode(Wicket.fixEvent(event))) {
case KEY_ENTER:
case KEY_UP:
case KEY_DOWN:
case KEY_ESC:
case KEY_TAB:
case KEY_RIGHT:
case KEY_LEFT:
case KEY_SHIFT:
case KEY_ALT:
case KEY_CTRL:
return Wicket.stopEvent(event);
break;
default:
if (typeof objonchange == "function")objonchange();
}
return null;
}
obj.onpaste = function(event) {
if (typeof objonchange == "function"){
setTimeout(function() {
objonchange();
}, 10);
}
return null;
}
obj.oncut = function(event) {
if (typeof objonchange == "function"){
setTimeout(function() {
objonchange();
}, 10);
}
return null;
}
} else {
obj.addEventListener('input', obj.onchange, true);
}
obj.onchange = function(event) {
Wicket.stopEvent(event);
}
}

var wicketThrottler = Wicket.throttler;
function wicketAjaxGet(url, successHandler, failureHandler, precondition, channel) {
var call = new Wicket.Ajax.Call(url, successHandler, failureHandler, channel);
if (typeof(precondition) != "undefined" && precondition != null) {
call.request.precondition = precondition;
}
return call.call();
}
function wicketAjaxPost(url, body, successHandler, failureHandler, precondition, channel) {
var call = new Wicket.Ajax.Call(url, successHandler, failureHandler, channel);
if (typeof(precondition) != "undefined" && precondition != null) {
call.request.precondition = precondition;
}
return call.post(body);
}
function wicketSubmitForm(form, url, submitButton, successHandler, failureHandler, precondition, channel) {
var call = new Wicket.Ajax.Call(url, successHandler, failureHandler, channel);
if (typeof(precondition) != "undefined" && precondition != null) {
call.request.precondition = precondition;
}
return call.submitForm(form, submitButton);
}
function wicketSubmitFormById(formId, url, submitButton, successHandler, failureHandler, precondition, channel) {
var call = new Wicket.Ajax.Call(url, successHandler, failureHandler, channel);
if (typeof(precondition) != "undefined" && precondition != null) {
call.request.precondition = precondition;
}
return call.submitFormById(formId, submitButton);
}
wicketSerialize = Wicket.Form.serializeElement;
wicketSerializeForm = Wicket.Form.serialize;
wicketEncode = Wicket.Form.encode;
wicketDecode = Wicket.decode;
wicketAjaxGetTransport = Wicket.Ajax.getTransport;

Wicket.Ajax.registerPreCallHandler(function() {
if (typeof(window.wicketGlobalPreCallHandler) != "undefined") {
var global=wicketGlobalPreCallHandler;
if (global!=null) {
global();
}
}
});
Wicket.Ajax.registerPostCallHandler(function() {
if (typeof(window.wicketGlobalPostCallHandler) != "undefined") {
var global=wicketGlobalPostCallHandler;
if (global!=null) {
global();
}
}
});
Wicket.Ajax.registerFailureHandler(function() {
if (typeof(window.wicketGlobalFailureHandler) != "undefined") {
var global=wicketGlobalFailureHandler;
if (global!=null) {
global();
}
}
});

Wicket.Focus = {
lastFocusId : "",
setFocus: function(event)
{
event = Wicket.fixEvent(event);


var target = event.target ? event.target : event.srcElement;
if (target) {
Wicket.Focus.lastFocusId=target.id;
Wicket.Log.info("focus set on " + Wicket.Focus.lastFocusId);
}
},
blur: function(event)
{
event = Wicket.fixEvent(event);


var target = event.target ? event.target : event.srcElement;
if (target && Wicket.Focus.lastFocusId==target.id) {
Wicket.Focus.lastFocusId=null;
Wicket.Log.info("focus removed from " + target.id);
}
},
setFocusOnId: function(id)
{
Wicket.Focus.lastFocusId=id;
Wicket.Log.info("focus set on " + Wicket.Focus.lastFocusId + " from serverside");
},
getFocusedElement: function()
{
if (typeof(Wicket.Focus.lastFocusId) != "undefined" && Wicket.Focus.lastFocusId != "" && Wicket.Focus.lastFocusId != null)
{
Wicket.Log.info("returned focused element: " + Wicket.$(Wicket.Focus.lastFocusId));
return Wicket.$(Wicket.Focus.lastFocusId);
}
return;
},
requestFocus: function()
{
if (typeof(Wicket.Focus.lastFocusId) != "undefined" && Wicket.Focus.lastFocusId != "" && Wicket.Focus.lastFocusId != null)
{
var toFocus = Wicket.$(Wicket.Focus.lastFocusId);
if (toFocus != null && typeof(toFocus) != "undefined") {
Wicket.Log.info("Calling focus on " + Wicket.Focus.lastFocusId);
try {
toFocus.focus();
} catch (ignore) {
}
}
else
{
Wicket.Focus.lastFocusId = "";
Wicket.Log.info("Couldn't set focus on " + Wicket.Focus.lastFocusId + " not on the page anymore");
}
}
else
{
Wicket.Log.info("last focus id was not set");
}
},
setFocusOnElements: function (elements)
{
for (var i=0; i< elements.length; i++)
{
if (elements[i].wicketFocusSet != true)
{
Wicket.Event.add(elements[i],'focus',Wicket.Focus.setFocus);
Wicket.Event.add(elements[i],'blur',Wicket.Focus.blur);
elements[i].wicketFocusSet = true;
}
}
},
attachFocusEvent: function()
{
Wicket.Focus.setFocusOnElements(document.getElementsByTagName("input"));
Wicket.Focus.setFocusOnElements(document.getElementsByTagName("select"));
Wicket.Focus.setFocusOnElements(document.getElementsByTagName("textarea"));
Wicket.Focus.setFocusOnElements(document.getElementsByTagName("button"));
Wicket.Focus.setFocusOnElements(document.getElementsByTagName("a"));
}
}
Wicket.Event.addDomReadyEvent(Wicket.Focus.attachFocusEvent);
function wicketAjaxDebugEnabled() {
if (typeof(wicketAjaxDebugEnable)=="undefined") {
return false;
} else {
return wicketAjaxDebugEnable==true;
}
}
function wicketKeyCode(event) {
if (typeof(event.keyCode)=="undefined") {
return event.which;
} else {
return event.keyCode;
}
}
function wicketGet(id) {
return Wicket.$(id);
}
function wicketShow(id) {
var e=wicketGet(id);
if (e!=null) {
e.style.display = "";
}
}
function wicketHide(id) {
var e=wicketGet(id);
if (e!=null) {
e.style.display = "none";
}
}