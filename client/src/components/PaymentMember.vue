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
        <img src="https://img.icons8.com/ios/96/000000/wallet.png">
        <h1 class="display-1 font-weight-bold mb-3">ชำระเงินค่าสมาชิก</h1>
      </v-flex>
    </v-layout>
    
    <v-row justify="center">
      <v-col cols="5">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="ID ผู้ใช้บริการ"
                prepend-icon= "mdi mdi-account"
                v-model="payment.customerId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              
              <p v-if="customerCheck != ''"> 
                <v-row justify="center">
                <MARQUEE behavior=alternate direction=left scrollAmount=3 width="4%"><font face=Webdings>3</font>
                </MARQUEE><MARQUEE scrollAmount=1 direction=left width="2%">
                  | | |</MARQUEE>Welcome!! : <b>{{ customerName}}</b><MARQUEE scrollAmount=1 direction=right width="2%">
                  | | |</MARQUEE><MARQUEE behavior=alternate direction=right scrollAmount=3 width="4%">
                  <font face=Webdings>4</font></MARQUEE>
                  </v-row>
               </p>
            </v-col>

            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findCustomer" depressed large color="#009900" style="color:#FFFFFF">ค้นหา</v-btn>
              </div>
            </v-col>
          </v-row>

          <v-row>
              <v-col cols="10">
                <v-select
                  label="พนักงานที่ทำรายการ"
                  prepend-icon= "mdi-account-edit"
                  outlined
                  v-model="payment.employeeId"
                  :items="employee"
                  item-text="employeename"
                  item-value="employeeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกประเภทผู้ใช้บริการ"
                  prepend-icon= "mdi-account-box"
                  outlined
                  v-model="payment.customertypeId"
                  :items="customerType"
                  item-text="customertypename"
                  item-value="customertypeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>
           
            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกช่วงระยะเวลา"
                  outlined
                  prepend-icon= "mdi-calendar-clock"
                  v-model="payment.timerangeId"
                  :items="timeRange"
                  item-text="timerangename"
                  item-value="timerangeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  style ="margin-top:-30px"
                ></v-select>
               </v-col>
            </v-row>
            
            <v-row justify="center">
              <v-col cols="10">
                <v-btn-toggle group >
                  <v-btn @click="savePayment" style="color:#FFFFFF" :class="{ black: !valid, green: valid } ">ยืนยัน</v-btn>
                  <v-btn @click="clear" color="#D50000" style="color:#FFFFFF">ยกเลิก</v-btn>
                  <v-btn @click="viewPayment" color="#0000FF" style="color:#FFFFFF">ดูผลการชำระเงิน</v-btn>
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
  name: 'paymentMember',
  components: {
    },
   data () {
    return { 
       payment: {
        customerId: null,
        customertypeId: null,
        timerangeId: null,
        employeeId: null,
      },
      timeRange: null,
      customerType: null,
      employee: null,
    
      valid: false,
      customerCheck: false,
      customerName: null,
    }
  },
  methods: {
    /* eslint-disable no-console */
    getcustomerType(){
      http
        .get("/customertype" )
        .then(response =>{
            this.customerType = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
    getemployee(){
      http
        .get("/employee")
        .then(response =>{
            this.employee = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
    getTimeRange() {
      http
        .get("/timerange")
        .then(response => {
          this.timeRange = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        })
    },
    
    findCustomer() {
      http
        .get("/customer/" + this.payment.customerId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.customerName = response.data.customername;
            this.customerCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
      savePayment() {
      http
        .post("/paymentmember/" +
            this.payment.customerId +
            "/" +
            this.payment.employeeId +
            "/" +
            this.payment.customertypeId +
            "/" +
            this.payment.timerangeId,
          this.payment
        )
        .then(response => {
          console.log(response);
          alert("บันทึกข้อมูลการชำระเงินสำเร็จ !!");
          this.$router.push("/viewpayment");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    viewPayment(){
      this.$router.push("/viewpayment");
    },
    clear() {
      this.$refs.form.reset();
     
     this.customerCheck = false;
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
      this.getcustomerType();
      this.getTimeRange();
      this.getemployee();
    }
    
  },
  mounted() {
      this.getcustomerType();
      this.getTimeRange();
      this.getemployee();
  }
}
</script>
