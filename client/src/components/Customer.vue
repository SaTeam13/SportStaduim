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
  
<v-card style="width:70%; margin:auto; background-color:#FFFFFF">
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">สมัครสมาชิก</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          
            <v-col cols="10">
              <v-text-field
                v-model="customer.customername"
                :rules="[(v) => !!v || 'Item is required']"
                label="Name" 
                required
              ></v-text-field>
            </v-col>
              
              <v-col cols="10">
                <v-select
                  label="Gender"
                  v-model="customer.genderid"
                  :items="gender"
                  item-text="sex"
                  item-value="genderid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

              <v-col cols="10">
              <v-text-field
                v-model="customer.address"
                :rules="[(v) => !!v || 'Item is required']"
                label="Address"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="Province"
                  v-model="customer.provinceid"
                  :items="province"
                  item-text="provincename"
                  item-value="provinceid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

              <v-col cols="10">
              <v-text-field
                v-model="customer.telephone"
                :rules="[(v) => !!v || 'Item is required']"
                label="Telephone"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="Category"
                  v-model="customer.categoryid"
                  :items="category"
                  item-text="type"
                  item-value="categoryid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn-toggle group >
                  <v-btn @click="saveCustomer" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                  <v-btn  @click="clear">ยกเลิก</v-btn>
                  <v-btn @click="viewCustomer" color="#000000" style="color:#FFFFFF">ดูบันทึก</v-btn>
                </v-btn-toggle>
              </v-col>
            </v-row>
            <br />
        </v-form>
      </v-col>
        </v-row>
  </v-container>
  </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "customer",
  data() {
    return {
      customer: {
        customername: null,
        genderid: null,
        address: null,
        provinceid: null,
        telephone: null,
        categoryid: null
      },
      gender:null,
      province:null,
      category:null,
      valid: false
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Gender ใส่ combobox
    getgender() {
      http
        .get("/gender")
        .then(response => {
          this.gender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // ดึงข้อมูล Province ใส่ combobox
    getprovince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


    // ดึงข้อมูล Category ใส่ combobox
    getcategory() {
      http
        .get("/category")
        .then(response => {
          this.category = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


    // function เมื่อกดปุ่ม submit
    saveCustomer() {
      http
        .post(
          "/customer/" +
            this.customer.customername +
            "/" +
            this.customer.genderid +
            "/" +
            this.customer.address +
            "/" +
            this.customer.provinceid +
            "/" +
            this.customer.telephone +
            "/" +
            this.customer.categoryid,
          this.customer
        )
        .then(response => {
          console.log(response);
          this.$router.push("/viewcustomer");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
     viewCustomer(){
      this.$router.push("/viewcustomer");
    },
    clear() {
      this.$refs.form.reset();
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
      this.getgender();
      this.getprovince();
      this.getcategory();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getgender();
    this.getprovince();
    this.getcategory();
  }
};
</script>