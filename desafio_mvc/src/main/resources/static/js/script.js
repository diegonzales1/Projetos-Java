$(function() {
	$('[rel="tooltip"]').tooltip();
})

//Modal Quarto
$('#ConfirmaExclusaoModalQuarto').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoQuarto = button.data('codigo');
	var quantidadeQuarto = button.data('quarto');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoQuarto);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir a quantidade <strong>' + quantidadeQuarto + '</strong> de quartos?');
});



//Modal Categoria
$('#ConfirmaExclusaoModal').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoCategoria = button.data('codigo');
	var nomeCategoria = button.data('categoria');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoCategoria);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir a Categoria <strong>' + nomeCategoria + '</strong> ?');
});

//Modal Município
$('#ConfirmaExclusaoModalMunicipio').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoMunicipio = button.data('codigo');
	var nomeMunicipio = button.data('municipio');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoMunicipio);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomeMunicipio + '</strong> ?');
});



//Modal Negócio
$('#ConfirmaExclusaoModalNegocio').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoNegocio = button.data('codigo');
	var nomeNegocio = button.data('negocio');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoNegocio);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomeNegocio + '</strong> ?');
});

//Modal Estado
$('#ConfirmaExclusaoModalEstado').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoEstado = button.data('codigo');
	var nomeEstado = button.data('estado');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoEstado);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomeEstado + '</strong> ?');
});


//Modal Bairro
$('#ConfirmaExclusaoModalBairro').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoBairro = button.data('codigo');
	var nomeBairro = button.data('bairro');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoBairro);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomeBairro + '</strong> ?');
});


//Modal Imóvel
$('#ConfirmaExclusaoModalImovel').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoImovel = button.data('codigo');
	var nomeImovel = button.data('imovel');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoImovel);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomeImovel + '</strong> ?');
});

