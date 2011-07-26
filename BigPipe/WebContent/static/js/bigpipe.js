var BigPipe = function () {
    this.registerComponent = function (p) {
    	$(document).ready(function() {
    		$('#' + p.name + 'component').html(p.content);
    	});
    };
};

var bigpipe = new BigPipe();