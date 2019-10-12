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
  
<v-card style="width:90%; margin:auto; background-color:#FFFFFF">
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">BORROW</h1>
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
                label="ต้องการลบ ID การยืมอุปกรณ์: "
                prepend-icon= "mdi mdi-delete-forever"
                v-model="Borrow.borrowid"
              ></v-text-field>
                 <p v-if="borrowCheck != ''">ID การยืมอุปกรณ์ที่จะทำการลบ : {{borrowid}}
                        <v-btn class="" @click="deleteborrow" color="#D50000" style="color:#FFFFFF" >ลบ</v-btn>
                 </p>
              </v-col>
            
            <v-col cols="2">
              <div class="">
                 <v-btn @click="findborrow" depressed large color="#000000" style="color:#FFFFFF;">ยืนยัน</v-btn>
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
  name: "viewborrow",
  data() {
    return {
      search: '',
      headers: [
        
        {
         text: "ID การยืม",
          align: "left",
          sortable: false,
          value: "borrowid"

        },
        { text: "ชื่อผู้ใช้บริการ", value: "nameborrow" },
        { text: "ชื่ออุปกรณ์", value: "equipment.equipment" },
        { text: "ประเภท", value: "typeequipmentid.typename" },
        { text: "จำนวน", value: "numberequipment" },
        { text: "วันที่ยืม", value: "borrowdate" },
        { text: "ชื่อพนักงาน", value: "employeename.employeename" },
        
      ],
      items: [],
      Borrow: {
        borrowid: null,
      },
      valid: false,
      borrowCheck: false,
      borrowid: null,
    };
  },
  methods: {
    /* eslint-disable no-console */
     getborrow() {
      http
        .get("/borrow")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
     findborrow() {
      http
        .get("/borrow/" + this.Borrow.borrowid)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.borrowid = response.data.borrowid;
            this.borrowCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deleteborrow() {
      http
        .delete("/borrow/" + this.Borrow.borrowid)
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
      this.getborrow();
    }
    /* eslint-disable no-console */
  },
  mounted() {
      this.getborrow();
  }
};
</script>
