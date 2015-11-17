/**
 * @author cdd
 */
(function() {
	function addEvent(el, type, fn) {
		if (el.addEventListener) {
			el.addEventListener(type, fn, false);
		} else if (el.attachEvent) {
			el.attachEvent('on' + type, function() {
				fn.call(el);
			});
		} else {
			throw new Error('not supported or DOM not loaded');
		}
	}

	function removeNode(el) {
		el.parentNode.removeChild(el);
	}
	var getUID = (function() {
		var id = 0;
		return function() {
			return 'cddajaxexport' + id++;
		};
	})();
	var toElement = (function() {
		var div = document.createElement('div');
		return function(html) {
			div.innerHTML = html;
			var el = div.firstChild;
			return div.removeChild(el);
		};
	})();
	window.weather= function(button, form, options) {
		this._settings = {
			action : 'haha.html',
			filename : 'xxxx',
			url:'http://m.weather.com.cn/m/pn6/weather.htm'
		};
		for ( var i in options) {
			if (options.hasOwnProperty(i)) {
				this._settings[i] = options[i];
			}
		}
		this.init();
		alert(this._settings.url);
	
	};
	weather.prototype = {
		setData : function(data) {
			this.settings.data = data;
		},
		_createForm : function(iframe) {
			var settings = this._settings;
			var form = toElement('<form method="post" enctype="multipart/form-data"></form>');
			form.setAttribute('action', settings.action);
			form.setAttribute('target', iframe.name);
			form.style.display = 'none';
			document.body.appendChild(form);
			var inp_arr = this._input;
			for ( var i = 0; i < inp_arr.length; i++) {
				form.appendChild(this._input[i]);
			}
		},
		_createIframe : function(url) {
			var id = getUID();
			var iframe = toElement('<iframe src="javascript:false;" name="'
					+ id + '"/>');
			iframe.setAttribute('id', id);
			iframe.setAttribute('src',url);
			iframe.style.display = 'none';
			document.body.appendChild(iframe);
			return iframe;
		},
		_createScript:function(url){
			var scriptBlock = document.createElement("script");
			 scriptBlock.src =url;
			 scriptBlock.type = "text/javascript";
			 scriptBlock.language = "javascript";
			 document.getElementsByTagName("head")[0].appendChild(scriptBlock);
			return scriptBlock;
		},
		_createInput : function() {
			var filename = this._settings.filename;
			try {
				var input = document
						.createElement("<input type='hidden' id='45646' name='filename' value='"
								+ filename + "'/>");
			} catch (e) {
				input.setAttribute('type', 'hidden');
				input.setAttribute('id', "45646");
				input.setAttribute('name', filename);
			}
			return input;
		},
		_setSize : function() {
			var size = this._settings.size;
			document.getElementById("page_size").value = size;
		},
		_resetSize : function() {
			document.getElementById("page_size").value = this._pageSize;
		},
		init : function() {
			var self = this, settings = this._settings;
			var scriptBlock = self._createScript(settings.url);
			self._getResponse(scriptBlock);
		},
		_getResponse : function(iframe) {
			alert("");
			addEvent(iframe, 'load', function() {
				var toDeleteFlag = false;
				if (iframe.src == "javascript:'%3Chtml%3E%3C/html%3E';"
						|| iframe.src == "javascript:'<html></html>';") {
					if (toDeleteFlag) {
						setTimeout(function() {
							removeNode(iframe);
						}, 0);
					}
					return;
				}
			});
			
			//var doc = iframe.contentDocument ? iframe.contentDocument: window.frames[iframe.id].document;
			if (doc.readyState && doc.readyState != 'complete') {
				return;
			}
			alert(doc.innerHTML);
			if (doc.body && doc.body.innerHTML == "false") {
				return;
			}

			toDeleteFlag = true;
			iframe.src = "javascript:'<html></html>';";

		},
		_rerouteClicks : function() {
			var self = this;
			addEvent(self._button, "mouseover", function() {
				if (!self._button) {
					return;
				}
				if (!self._form) {
					return;
				}
			});
			addEvent(self._button, "click", function() {
				self.submit();
			});
		}

	};
})();
