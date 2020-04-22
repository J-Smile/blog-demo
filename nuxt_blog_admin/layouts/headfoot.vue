<template>
  <div>
    <Head></Head>
    <nuxt/>
    <Footer></Footer>
  </div>
</template>

<script>
  import Head from "../components/Head";
  import Footer from "../components/Footer";

  export default {
    components: {Head, Footer},
    head: {
      link: [
        {rel: 'stylesheet', href: '/css/bootstrap.min.css'},
        {rel: 'stylesheet', href: '/css/nprogress.css'},
        {rel: 'stylesheet', href: '/css/style.css'},
      ]
    },
  }
</script>

<style>

</style>
