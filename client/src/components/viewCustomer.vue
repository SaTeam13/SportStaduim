<template>
<v-container>
<v-app-bar style = "background: white;" app>
      <v-toolbar-title  class="headline text-uppercase" >
        <span class="font-weight">SportStaduim</span>   
      </v-toolbar-title>
      <v-row justify="center" style="margin-right:190px">
      <v-btn-toggle group>
      <v-btn @click="pushReserve" color="#00E676" style="color:#000000">จองสนาม</v-btn>
      <v-btn @click="pushBorrow" color="#76FF03" style="color:#000000">ยืมอุปกรณ์</v-btn>
      <v-btn @click="pushCustomer" color="#00E676" style="color:#000000">สมัครสมาชิก</v-btn>
      <v-btn @click="pushEmployee" color="#76FF03" style="color:#000000">ข้อมูลพนักงาน</v-btn>
      <v-btn @click="pushPayment" color="#00E676" style="color:#000000">ชำระเงินค่าสมาชิก</v-btn>
      <v-btn @click="pushSportEquipment" color="#76FF03" style="color:#000000">ข้อมูลอุปกรณ์</v-btn>
      </v-btn-toggle>
      
      </v-row>
  </v-app-bar>
  
<v-card style="width:100%; margin:auto; background-color:#FFFFFF">
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">ข้อมูลสมาชิก</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="5">
        <v-text-field v-model="search"
          label="ค้นหา"
          prepend-icon= "mdi mdi-file-find"
          outlined hide-details
        ></v-text-field>
        </v-col>
        <v-col cols="12">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1" :search="search">
        </v-data-table>
        </v-col>
    </v-row>
  </v-container>
   </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "ViewCustomer",
  data() {
    return {
      search:'',
       headers: [
         {
          text: "Customer ID",
          align: "left",
          sortable: false,
          value: "customerid"
        },
        {text: "Customer",value: "customername" },
        {
          text: "Gender",
          align: "left",
          sortable: false,
          value: "genderid.sex"
        },
        { text: "Address", value: "address" },
        {
          text: "Province",
          align: "left",
          sortable: false,
          value: "provinceid.provincename"
        },
        { text: "Telephone", value: "telephone" },
        {
          text: "Category",
          align: "left",
          sortable: false,
          value: "categoryid.type"
        }
       ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล Customer ใส่ combobox
    getCustomer() {
      http
        .get("/customer")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    pushReserve(){
      this.$router.push("/reservation");
    },
  pushBorrow(){
      this.$router.push("/borrow");
    },
  pushCustomer(){
      this.$router.push("/customer");
    },
  pushEmployee(){
      this.$router.push("/employee");
    },
  pushPayment(){
      this.$router.push("/paymentmember");
    },
    pushSportEquipment(){
      this.$router.push("/sportequipment");
    },
    refreshList() {
      this.getCustomer();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getCustomer();
  }
};
</script>
