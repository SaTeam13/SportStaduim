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
        <span class="mdi mdi-calendar mdi-48px "></span>
        <h1 class="display-1 font-weight-bold mb-3">แบบฟอร์มบันทึกการจองสนามกีฬา</h1>
      </v-flex>
    </v-layout>
    
    <v-row justify="center">
      <v-col cols="5">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                clearable
                label="ID ผู้ใช้บริการ"
                prepend-icon= "mdi mdi-account"
                v-model="reservation.customerId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              <p v-if="customerCheck != ''">ชื่อผู้ใช้บริการ : {{customerName}}</p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findCustomer" depressed large color="#00E676" style="color:#000000">ยืนยัน</v-btn>
              </div>
            </v-col>
          </v-row>

          <v-row>
              <v-col cols="10">
                <v-select
                  label="พนักงานที่ทำรายการ"
                  prepend-icon= "mdi mdi-account-card-details"
                  clearable
                  v-model="reservation.employeeId"
                  :items="employees"
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
                  label="เลือกประเภทสนาม"
                  prepend-icon= "mdi mdi-football"
                  clearable
                  v-model="reservation.fieldCategoryId"
                  :items="fieldCategorys"
                  item-text="field"
                  item-value="fieldid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="20">
                <v-menu
                  v-model="menu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
        <template v-slot:activator="{ on }">
          <v-text-field style= "width:82%"
            prepend-icon= "mdi mdi-calendar"
            v-model="date"
            label="เลือกวันที่จองสนาม"
            clearable
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" @input="menu = false"></v-date-picker>
      </v-menu>

              </v-col>
            </v-row>
            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกเวลาจองสนาม"
                  clearable
                  prepend-icon= "mdi mdi-clock"
                  v-model="reservation.timeTableId"
                  :items="timeTables"
                  item-text="timeString"
                  item-value="timetableid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  style ="margin-top:-30px"
                ></v-select>
              </v-col>
            </v-row>
            
            <v-row justify="center">
              <v-col cols="10">
                <v-btn-toggle group >
                  <v-btn @click="saveReservation" style="color:#FFFFFF" :class="{ red: !valid, green: valid } ">บันทึก</v-btn>
                  <v-btn @click="clear" color="#D50000" style="color:#FFFFFF">ยกเลิก</v-btn>
                  <v-btn @click="viewReserve" color="#000000" style="color:#FFFFFF">ดูบันทึก</v-btn>
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
  name: 'reserveTheField',
  components: {
    },
   data () {
    return { 
      date: new Date().toISOString().substr(0, 10),
      menu: false,
       reservation: {
        customerId: null,
        fieldCategoryId: null,
        timeTableId: null,
        employeeId: null
      },
      fieldCategorys: null,
      timeTables: null,
      employees: null,
      valid: false,
      customerCheck: false,
      customerName: null,
    }
  },
  methods: {
    /* eslint-disable no-console */
     // ดึงข้อมูล fieldCategory ใส่ combobox
    getFieldCategorys(){
      http
        .get("/fieldcate")
        .then(response =>{
            this.fieldCategorys = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
     // ดึงข้อมูล employee ใส่ combobox
    getEmployees(){
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
    },
     // ดึงข้อมูล timeTable ใส่ combobox
    getTimeTables() {
      http
        .get("/timetable")
        .then(response => {
          this.timeTables = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        })
    },
    findCustomer() {
      http
        .get("/customer/" + this.reservation.customerId)
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
      saveReservation() {
      http
        .post("/reservation/" +
            this.reservation.customerId +
            "/" +
            this.reservation.fieldCategoryId +
            "/" +
            this.reservation.timeTableId +
            "/" +
            this.reservation.employeeId +
            "/" +
             this.date,
          this.reservation
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
          this.$router.push("/viewreserve");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;

    },
    viewReserve(){
      this.$router.push("/viewreserve");
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
      this.getFieldCategorys();
      this.getTimeTables();
      this.getEmployees();
    }
    
    /* eslint-enable no-console */
  },
  mounted() {
      this.getFieldCategorys();
      this.getTimeTables();
      this.getEmployees();
  }
}
</script>
