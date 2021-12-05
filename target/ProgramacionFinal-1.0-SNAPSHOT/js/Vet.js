$(document).ready(function(){
    $("body").hide().fadeIn(3000);
});

$(document).ready(function(){
    $("#petedit-button").click(function(){
        $(this).next('#editpet').slideToggle();
        $(this).toggleClass('active');


    });

});
