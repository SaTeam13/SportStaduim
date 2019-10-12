<template>
  <v-app>
  <div class="bg">  
     <v-content>
      <router-view />
    </v-content>
    </div> 
  </v-app>
</template>
<script>
  export default {
  name: 'App',

  methods: { 
  Reserve(){
      this.$router.push("/reservation");
    },
  Borrow(){
      this.$router.push("/borrow");
    },
  Customer(){
      this.$router.push("/customer");
    },
  Employee(){
      this.$router.push("/employee");
    },
  Payment(){
      this.$router.push("/paymentmember");
    },
    SportEquipment(){
      this.$router.push("/sportequipment");
    },
    },
};

</script>