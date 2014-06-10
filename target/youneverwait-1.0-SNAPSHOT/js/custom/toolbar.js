//button animate upon hover
	jQuery('.anchorbutton').hover(function(){
		jQuery(this).stop().animate({
			backgroundColor: '#222', 
			borderColor: '#111', 
			color: '#fff'
		},500);
		jQuery(this).find('span').stop().animate({
			backgroundColor: '#333', 
			borderColor: '#444'
		},500);
	},function(){
		jQuery(this).stop().animate({
			backgroundColor: '#f7f7f7',
			borderColor: '#ccc',
			color: '#333'
		},300);
		jQuery(this).find('span').stop().animate({
			backgroundColor: '#fff', 
			borderColor: '#ddd'
		},300);
	});