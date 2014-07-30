$(document).ready(function() {
  // $.get("http://dogecoinerme.appspot.com/total.jsp?uid=sol&domain=block.io"), function(data) {
  //   console.log(data.trim());
  // }
  $('#counter').html(counter.toString());
  // click functionality on the slider 
  $('#slider').click(function() {
    //access the counter app and sets it's value to counter, and 
    $.get("http://dogecoinerme.appspot.com/oneclick.jsp?uid=sol&domain=block.io", function(data) {
      console.log(data.trim());
      counter = data.trim();
      $('#counter').html(counter.toString());
    });
    $('#plus').css({visibility: 'visible'});
    if(counter>=1) {
      $('#icon').animate({marginLeft:"0px"}, 200);
      $('#slider').css({backgroundColor:"rgb(0, 255, 0)"});
      $('#icon').css({pointerEvents:"auto"});
    } else {
      $('#icon').animate({marginLeft:'38px'}, 200);
      $('#slider').css({backgroundColor:'grey'});
      $('#icon').css({pointerEvents:'none'});
    }
  });
  // click functionality on the icon
  $('#icon').click(function() {
    counter -= 2;
    $('#counter').html(counter.toString());
  });
  $('#plus').click(function() {
    $('#counter').html(counter.toString());
  });
  // $.get({
  //   url: "http://dogecoinerme.appspot.com/total",
  //   data: {
  //     uid: "sol",
  //     domain: "block.io"
  //   },
  //   success: success,
  //   dataType: jsp
  // });

  // $.post({
  //   url: "http://dogecoinerme.appspot.com/oneclick",
  //   data: {
  //     uid: "sol",
  //     domain: "sochain"
  //   },
  //   success: success,
  //   dataType: json
  // });
})