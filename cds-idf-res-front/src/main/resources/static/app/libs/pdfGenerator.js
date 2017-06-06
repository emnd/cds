function genPDF(){
 var jsPDF = require('jsPdf');
		var pdf = new jsPDF('p', 'pt', 'letter');

		var specialElementHandlers = {
			'#telecharger' : function(element,render){ return true;}
		};
		  source = $('#pdfdiv')[0];
		   margins = {
		    top:50,
            left:60,
            width: 545
        };
		pdf.fromHTML(
            source,
            margins.left,
            margins.top,
			{
                'width': margins.width,
                'elementHandlers': specialElementHandlers
            },
            function(dispose) {
                // dispose: object with X, Y of the last line add to the PDF
                //          this allow the insertion of new lines after html
                 pdf.save('TestPdf.pdf');
             }
        );

	}

	$('#telecharger').click(function() {
	      console.log( " Are you ready!" );
	      genPDF();
	      console.log( " yes, I'm ready!" );
	    });