<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://vuejs.org/js/vue.js"></script>  
<style type="text/css">
.slide-enter-active, .slide-leave-active {
  transition: transform 0.5s
}
.slide-enter {
  transform: translateX(50px)
}
.slide-leave-active {
  transform: translateX(-50px);
}

p {
  position: absolute;
  margin: 0;
  font-size: 3em;
}
</style>
</head>

<body>
  <div id="app">
  <transition name="slide">
    <p :key="products[product]" style="text-align:center;margin">
    <img v-bind:src='products[product]' style="width:100px;height:100px;text-align:center;"></p>
  </transition>
</div>
  <script>
new Vue({
    el: '#app',
  data: {
    product: 0,
    products: ['/ec/img/kyabetu.jpg','/ec/img/piman.jpg','/ec/img/ninzin.jpg',
    	'/ec/img/tomato.jpg','/ec/img/nasu.jpg'],
    timer: null
  },
  computed: {
    btnString: function() {
      return this.timer === null ? "始める" : "止める";
    }
  },
  mounted: function() {
    this.onTimer();
  },
  methods: {
    nextSlide: function() {
      this.product = this.product < this.products.length - 1 ? this.product += 1 : 0;
    },
    onTimer: function () {
      this.timer = setInterval(() => {
        this.nextSlide();
      }, 2000)
    },
    offTimer: function () {
      if(this.timer === null) {
        this.onTimer();
      } else {
        clearInterval(this.timer);
        this.timer = null;
      }
    }
    
  }
})
</script>
</body>
</html>