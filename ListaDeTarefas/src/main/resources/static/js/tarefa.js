$('#ConfirmaExclusaoModal').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoTarefa = button.data('codigo');
	var descricaoTarefa = button.data('descricao');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoTarefa);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir a tarefa <strong>' + descricaoTarefa + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();

	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();

		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');

		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});

		response.done(function(e){
			var codigoTarefa = botaoReceber.data('codigo');
			$('[data-role=' + codigoTarefa + ']').html('<span class="label badge badge-success">'+ e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function (e){
			console.log(e);
			alert('Erro ao Confirmar Tarefa');
		});
		
	});

})
