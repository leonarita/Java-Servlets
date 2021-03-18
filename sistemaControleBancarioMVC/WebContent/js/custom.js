/**
 * 
 */

$(document).ready(function ()
{
	$('#dtPessoas').DataTable({
		"searching": true,
		"ordering": true,
		"paging": "full_numbers",
		"responsive": true,
		"language": { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Portuguese-Brasil.json" }
	});
	
	$('#dtContasComuns').DataTable({
		"searching": true,
		"ordering": true,
		"paging": "full_numbers",
		"responsive": true,
		"language": { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Portuguese-Brasil.json" }
	});
	
	$('.dataTables_length').addClass('bs-select');
});