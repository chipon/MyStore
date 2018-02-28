$(document).ready(function() { 
    
	// Drop Down Menu
	$('ul#main-menu').superfish({ 
        delay:       600,
        animation:   {opacity:'show',height:'show'},
        speed:       'fast',
        autoArrows:  true,
        dropShadows: false
    });

	// Accordion
	$( ".accordion" ).accordion( { autoHeight: false } );

	// Toggle
	$( ".toggle > .inner" ).hide();
	$(".toggle .title").toggle(function(){
		$(this).addClass("active").closest('.toggle').find('.inner').slideDown(200, 'easeOutCirc');
	}, function () {
		$(this).removeClass("active").closest('.toggle').find('.inner').slideUp(200, 'easeOutCirc');
	});

	// Tabs
	$(function() {
		$( "#tabs" ).tabs();
	});
	
	// Gallery Hover Effect
	jQuery(".gallery-item .gallery-thumbnail .zoom-wrapper").hover(function(){		
		jQuery(this).animate({ opacity: 1 }, 300);
	}, function(){
		jQuery(this).animate({ opacity: 0 }, 300);
	});
	
	// PrettyPhoto
	$(document).ready(function(){
		$("a[rel^='prettyPhoto']").prettyPhoto();
	});
	
	// Slides Loader
	$(".slider").removeClass("slide-loader");
	$(".slider-single").removeClass("slide-loader-single");
	
	// Mobile Menu

	// Create the dropdown base
	$("<select />").appendTo("#main-menu-wrapper");
      
	// Create default option "Go to..."
	$("<option />", {
		"selected": "selected",
		"value"   : "",
		"text"    : "Go to..."
	}).appendTo("#main-menu-wrapper select");
      
	// Populate dropdown with menu items
	$("#main-menu a").each(function() {
		var el = $(this);
		$("<option />", {
			"value"   : el.attr("href"),
			"text"    : el.text()
		}).appendTo("#main-menu-wrapper select");
	});
	
	// To make dropdown actually work
	$("#main-menu-wrapper select").change(function() {
		window.location = $(this).find("option:selected").val();
	});

	// Quantity Buttons
	$(function() {

		$(".qty-text").before('<input type="button" class="plusminus minus" id="minus1" value="-">');
		$(".qty-text").after('<input type="button" class="plusminus plus" id="plus1" value="+">');

		$(".plusminus").click(function() {
			var $button = $(this);
			var oldValue = $button.parent().find(".qty-text").val();
			
			if ($button.val() == "+") {
				var newVal = parseFloat(oldValue) + 1;
			} 
			
			else {		
				if (oldValue > 1) {
					var newVal = parseFloat(oldValue) - 1;
				}
				
				else {
					var newVal = 1;
				}
			}
	
			$button.parent().find(".qty-text").val(newVal);
	
	    });

	});
	
});

// Slider
$(window).load(function(){
  $('.slider').flexslider({
    animation: "slide",
	controlNav: false
  });
});

$(window).load(function(){
  $('.slider-single').flexslider({
    animation: "slide",
	controlNav: true,
	directionNav: false
  });
});