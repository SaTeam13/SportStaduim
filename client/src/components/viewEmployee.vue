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
        <h1 class="display-1 font-weight-bold mb-3">Employee</h1>
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
    
             
            <v-col cols="3">
              <v-text-field
                outlined
                label="ต้องการลบ ID พนักงาน: "
                prepend-icon= "mdi mdi-delete-forever"
                v-model="employee.employeeid"
              ></v-text-field>
                 <p v-if="employeeCheck != ''">ID พนักงานที่จะทำการลบ : {{employeeid}}
                        <v-btn class="" @click="deleteemployee" color="#D50000" style="color:#FFFFFF" >ลบ</v-btn>
                 </p>
              </v-col>
            
            <v-col cols="2">
              <div class="">
                 <v-btn @click="findemployee" depressed large color="#000000" style="color:#FFFFFF;">ยืนยัน</v-btn>
              </div>
            </v-col>
          </v-row>
  </v-container>
   </v-card>
  </v-container>
</template>


<script>
import http from "../http-common";
export default {
  name: "viewemployee",
  data() {
    return {
      search: '',
      headers: [
        
        {
         text: "ID พนักงาน",
          align: "left",
          sortable: false,
          value: "employeeid"

        },
        { text: "ชื่อพนักงาน", value: "employeename" },
        { text: "เพศ", value: "genderid.sex" },
        { text: "เลขบัตรประชาชน", value: "idcardnumber" },
        { text: "ที่อยู่", value: "address" },
        { text: "จังหวัด", value: "provinceid.provincename" },
        { text: "โทร.", value: "telephone" },
        { text: "ตำแหน่ง", value: "positionid.positionlevel" },
        { text: "เลขที่บัญชี", value: "banknum" },
        
      ],
      items: [],
      employee: {
        employeeid: null,
      },
      valid: false,
      employeeCheck: false,
      employeeid: null,
    };
  },
  methods: {
    /* eslint-disable no-console */
     getemployee() {
      http
        .get("/employee")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
     findemployee() {
      http
        .get("/employee/" + this.employee.employeeid)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.employeeid = response.data.employeeid;
            this.employeeCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deleteemployee() {
      http
        .delete("/employee/" + this.employee.employeeid)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          alert("ทำการลบรายการจองเรียบร้อย");
          location.reload();
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
      this.getemployee();
    }
    /* eslint-disable no-console */
  },
  mounted() {
      this.getemployee();
  }
};
</script>
