/**
 * 
 */

$(document).ready(function ()
{
	/**
	 * Definindo as DataTables do Sistema
	 */
	 
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
	
	
	/**
	 * Controle da obrigatoriedade do campo de submissão de arquivo da foto de perfil
	 * na página de atualização de pessoa.
	 */
	 
	$("#chkAlterarFotoPerfil").change(function() {
	    if(this.checked) {
	    	$("#fileFotoPerfil").removeAttr("disabled");
	        $("#fileFotoPerfil").attr("required", "required");
	    }
	    else {
	        $("#fileFotoPerfil").removeAttr("required");
	        $("#fileFotoPerfil").attr("disabled", "disabled");
	    }
	});
});