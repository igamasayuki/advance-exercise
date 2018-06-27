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
  transform: translateX(100px)
}
.slide-leave-active {
  transform: translateX(-100px);
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
    <p :key="products[product]" style="text-align:center">
    <img v-bind:src='products[product]' style="width:400px;height:300px;text-align:center;"></p>
  </transition>
</div>
  <script>
new Vue({
    el: '#app',
  data: {
    product: 0,
    products: ['/ec-201804d/img/kyabetu.jpg','/ec-201804d/img/piman.jpg','/ec-201804d/img/ninzin.jpg',
   	'/ec-201804d/img/tomato.jpg','/ec-201804d/img/nasu.jpg','/ec-201804d/img/jagaimo.jpg','/ec-201804d/img/kyuri.jpg',
   	'/ec-201804d/img/ninniku.jpg','/ec-201804d/img/nira.jpg','/ec-201804d/img/remon.jpg','/ec-201804d/img/satumaimo.jpg',
   	'/ec-201804d/img/syouga.jpg','/ec-201804d/img/tamanegi.jpg','/ec-201804d/img/toumorokosi.jpg','/ec-201804d/img/yuzu.jpg'],
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