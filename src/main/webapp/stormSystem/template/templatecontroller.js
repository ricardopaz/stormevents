stormapp.controller('TemplateController', function($scope, userService) {
	
	//Backgroud
	$.backstretch("UI/images/bg5.jpg", {
		speed : 500
	});
	
	$('#nav-accordion').dcAccordion({
        eventType: 'click',
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: 'slow',
        showCount: false,
        autoExpand: true,
        classExpand: 'dcjq-current-parent'
    });
	
    //sidebar toggle
    $(function() {
        function responsiveView() {
            var wSize = $(window).width();
            if (wSize <= 768) {
                $('#container').addClass('sidebar-close');
                $('#sidebar > ul').hide();
            }

            if (wSize > 768) {
                $('#container').removeClass('sidebar-close');
                $('#sidebar > ul').show();
            }
        }
        $(window).on('load', responsiveView);
        $(window).on('resize', responsiveView);
    });

    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
        	$("#nav-accordion").fadeOut("fast");
        	$("#main-content").animate({'margin-left' : '0px'});
        } else {
        	$("#nav-accordion").fadeIn("slow");
        	$("#main-content").animate({'margin-left' : '210px'});
        }
    	$("#sidebar").animate({width: 'toggle'});
    });

    // custom scrollbar
    $("#sidebar").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '3', cursorborderradius: '10px', background: '#404040', spacebarenabled:false, cursorborder: ''});

    $("html").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '6', cursorborderradius: '10px', background: '#404040', spacebarenabled:false,  cursorborder: '', zindex: '1000'});

    // widget tools

    jQuery('.panel .tools .fa-chevron-down').click(function () {
        var el = jQuery(this).parents(".panel").children(".panel-body");
        if (jQuery(this).hasClass("fa-chevron-down")) {
            jQuery(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
            el.slideDown(200);
        }
    });

    jQuery('.panel .tools .fa-times').click(function () {
        jQuery(this).parents(".panel").parent().remove();
    });


    //    tool tips

    $('.tooltips').tooltip();

    //    popovers

    $('.popovers').popover();



    // custom bar chart

    if ($(".custom-bar-chart")) {
        $(".bar").each(function () {
            var i = $(this).find(".value").html();
            $(this).find(".value").html("");
            $(this).find(".value").animate({
                height: i
            }, 2000);
        });
    }
	
	$scope.user = userService.getUser();
	var userEmail = $.cookie("userEmail");
	
	if(!$scope.user){
		if(userEmail){
			userService.getUserByEmail(userEmail).then(function(data){
				$scope.user = data.content;
			});
		}else{
			window.location.href = "#/login";
		}
	}
	
});