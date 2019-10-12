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
        <h1 class="display-1 font-weight-bold mb-3">SportEquipment</h1>
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
                label="ต้องการลบ List(ID): "
                prepend-icon= "mdi mdi-delete-forever"
                v-model="sportEquipment.Listid"
              ></v-text-field>
              <p v-if="ListCheck != ''">List ID ที่ต้องการลบ : {{Listid}}
                <v-btn class="" @click="deleteList" color="#D50000" style="color:#FFFFFF" >ลบ</v-btn>
              </p>
            </v-col>
            <v-col cols="2">
              <div class="">
                <v-btn @click="findList" depressed large color="#000000" style="color:#FFFFFF;">ยืนยัน</v-btn>
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
  name: "viewSportEquipment",
  data() {
    return {
      search: '',
      headers: [
        
        {
          text: "SportEquipment ID",
          align: "left",
          sortable: false,
          value: "sportequipmentid"

        },
        { text: "ชื่ออุปกรณ์", value: "equipment" },
        { text: "ชนิดกีฬา", value: "typeequipmentid.typename" },
        { text: "สถานะ", value: "typestatus.typestatus" },
        { text: "จำนวน", value: "number" },        
        { text: "ทำรายการโดย", value: "employeeid.employeename" },
        
      ],
      items: [],
       sportEquipment: {
       Listid: null,
      },
      valid: false,
      ListCheck: false,
      Listid: null,
    };
  },
  methods: {
    /* eslint-disable no-console */
    getList() {
      http
        .get("/sportEquipment")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    findList() {
      http
        .get("/sportEquipment/" + this.sportEquipment.Listid)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.Listid = response.data.sportequipmentid;
            this.ListCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deleteList() {
      http
        .delete("/sportEquipment/" + this.sportEquipment.Listid)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          alert("ทำการลบรายการข้อมูลของอุปกรณ์เรียบร้อย");
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
      this.getList();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getList();
  }
};
</script>
