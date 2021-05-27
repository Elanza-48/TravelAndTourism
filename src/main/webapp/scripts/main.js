function sendAlert(){
	/*First import '/scripts/mcx-dialog.js' and '/css/dialog-mobile.css' */
	mcxDialog.confirm('Are you sure u want to logout?',{
        sureBtnText: 'YES',
        cancelBtnText: 'NO',
        sureBtnClick: function(){
        	var form = document.createElement("form");
    	    form.setAttribute("method", "post");
    	    form.setAttribute("action", '../logout');
            document.body.appendChild(form);
    	    form.submit();
        }
    }); 
};
	
$(function() {
	$("#expiryDate").datepicker({
		dateFormat: 'mm/y',
		changeMonth: true,
		changeYear: true,
		minDate:'m',
		showButtonPanel: true,
		onClose: function(dateText, inst) {
			var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
			var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
			$(this).datepicker('setDate', new Date(year, month, 1));
		}
	});
});

function openPopupSelf(URL,V,K){
	window.open(`${URL}?val=${V}&key=${K}`,
			'_blank','scrollbars=yes, resizable=yes, height=300,width=280,top=200,left=300');
};
function openPopup(URL,V,K){
	window.open(`../${URL}?val=${V}&key=${K}`,
			'_blank','scrollbars=yes, resizable=yes, height=300,width=280,top=200,left=300');
};

function closePopup(){
	window.close();
};
