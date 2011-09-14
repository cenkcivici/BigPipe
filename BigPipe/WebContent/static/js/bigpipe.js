var BigPipe = function () {
    this.registerComponent = function (p) {
    	$(document).ready(function() {
    		$('#' + p.name).html(bigpipe.htmlDecode(p.content));
    	});
    };
    
    this.htmlDecode = function(input){
    	var e = document.createElement('div');
    	e.innerHTML = input;
    	return e.childNodes.length === 0 ? "" : e.childNodes[0].nodeValue;
    };
};

var bigpipe = new BigPipe();