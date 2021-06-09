$(function(){
	$("#user-table").dataTable({
		//日本語化
		language:{
			url:"/webjars/datatables-plugins/i18n/Japanese.json"
		},
		//ボタン有効化
	    dom: "Bfrtip",
	    buttons: ["excelHtml5", "csvHtml5", "print"]
	});
});