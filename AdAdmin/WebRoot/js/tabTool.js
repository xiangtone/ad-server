$(function() {
	$('.tab_block2 li a').click(
			function() {
				$(this).parent('li').addClass('click').siblings('li')
						.removeClass('click');
				$(this).addClass('click').parent('li').siblings('li').children(
						'a').removeClass('click');
				$(this).children('span').addClass('click').parent('a').parent(
						'li').siblings('li').children('a').children('span')
						.removeClass('click');
			});
	$('.sub_1').click(function() {
		$('.subblock_1').show().siblings('div').hide();
	});

	$('.sub_2').click(function() {
		$('.subblock_2').show().siblings('div').hide();
	});
	$('.sub_3').click(function() {
		$('.subblock_3').show().siblings('div').hide();
	});
	$('.sub_4').click(function() {
		$('.subblock_4').show().siblings('div').hide();
	});
	$('.sub_5').click(function() {
		$('.subblock_5').show().siblings('div').hide();
	});
	$('.sub_6').click(function() {
		$('.subblock_6').show().siblings('div').hide();
	});
	$('.sub_7').click(function() {
		$('.subblock_7').show().siblings('div').hide();
	});
});
