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
                var mois = new Date().getMonth()+1;
             pdf.save('pret_Materiel_Pdf_'+new Date().getDate()+'_'+mois+'_'+new Date().getFullYear()+'_'+new Date().getHours()+'_'+new Date().getMinutes()+'.pdf');
            }
        );

	}

