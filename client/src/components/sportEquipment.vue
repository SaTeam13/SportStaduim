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
      <br />
      <br />
      <br />
        <h1 style="margin-top: -80px;"  class="display-1 font-weight-bold mb-3">ระบบจัดการข้อมูลอุปกรณ์กีฬา</h1>
          </v-flex>
           </v-layout>
           <br/>

  

    <v-row justify="center">
      <v-col cols="5">
        <v-form>

        <v-row justify="center">     
        <!-- พนักงาน -->
            <v-col cols="10">
              <v-select
                label="พนักงาน"
                solo
                v-model="sportEquipment.employee"
                :items="employee"
                item-text="employeename"
                item-value="employeeid"
                :rules="[(v) => !!v || 'กรุณากรอกเลือกข้อมูล']"
                required
                style="margin-top: -30px;"
                prepend-icon="mdi mdi-account-card-details"
              ></v-select>
             </v-col>
             <br/>
        <!-- ชนิดกีฬา -->
            <v-col cols="10">
              <v-select
                label="ชนิดกีฬา"
                solo
                v-model="sportEquipment.typeEquipment"
                :items="typeEquipment"
                item-text="typename"
                item-value="typeequipmentid"
                :rules="[(v) => !!v || 'กรุณากรอกเลือกข้อมูล']"
                required
                style="margin-top: -30px;"
                prepend-icon="mdi mdi-basketball"
              ></v-select>
             </v-col>  

         <!-- ชื่ออุปกรณ์ -->
            <v-col cols="10">
              <v-text-field
                solo  
                label="ชื่ออุปกรณ์"
                v-model= "sportEquipment.equipment"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                style="margin-top: -30px;"                
               prepend-icon= "mdi mdi-tennis"
              ></v-text-field>
            </v-col>
            <br/> 

            <!-- สถานะอุปกรณ์ -->
            <v-col cols="10">
              <v-select
                label="สถานะอุปกรณ์"
                solo
                v-model="sportEquipment.typestatus"
                :items="typestatus"
                item-text="typestatus"
                item-value="statusid"
                :rules="[(v) => !!v || 'กรุณากรอกเลือกข้อมูล']"
                required
                style="margin-top: -30px;"
                prepend-icon="mdi mdi-mouse"
              ></v-select>
             </v-col>
             <br/>  

         <!-- จำนวน -->
            <v-col cols="10">
              <v-text-field
                solo  
                label="จำนวน"
                v-model= "sportEquipment.number"              
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                style="margin-top: -30px;"                
               prepend-icon= "mdi mdi-calculator"
              ></v-text-field>
            </v-col>       
        </v-row> 


          <v-row justify="center">
                <v-col cols="6">
                <v-btn-toggle group >
                  <v-btn @click="savesportEquipment" :class="{ red: !valid, green: valid }">บันทึก</v-btn> 
                  <v-btn @click="clear" color="#000000" style="color:#FFFFFF">ยกเลิก</v-btn>
                  <v-btn @click="viewSportEquipment" color="#000000" style="color:#FFFFFF">ดูบันทึก</v-btn>
                </v-btn-toggle>
                </v-col>

          </v-row>
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
  name: "sportEquipment",
  data() {
    return {
      sportEquipment: {
        employee:null,
        typeEquipment:null,
        equipment:null,
        typestatus:null,
        number:null,      
     },
      
        employee: null,
        typeEquipment:null,
        equipment:null,
        typestatus:null,
        number:null,
        valid: false,
        };
  },



  methods: {
    
     /* eslint-disable no-console */
        // ดึงข้อมูล employee ใส่ combobox
         getemployee() {
      http
        .get("/employee")
        .then(response => {
          this.employee = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


     /* eslint-disable no-console */
        // ดึงข้อมูล typeEquipment ใส่ combobox
         gettypeEquipment() {
      http
        .get("/typeEquipment")
        .then(response => {
          this.typeEquipment = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    /* eslint-disable no-console */
            // ดึงข้อมูล status ใส่ combobox
            getstatus() {
        http
            .get("/status")
            .then(response => {
            this.typestatus = response.data;
            console.log(response.data);
            })
            .catch(e => {
            console.log(e);
            });
        },

   savesportEquipment() {
      http
        .post(
          "/sportEquipment/" +
          this.sportEquipment.employee+"/"+
          this.sportEquipment.typeEquipment+"/"+
          this.sportEquipment.equipment+"/"+
          this.sportEquipment.typestatus+"/"+
          this.sportEquipment.number,
          this.sportEquipment
        )
       .then(response => {
         console.log(response);
         alert("บันทึกสำเร็จ")
         this.$router.push("/viewsportequipment");
       })
        .catch(e => {
          console.log(e);
        });
        
      this.submitted = true;
      
      },
      viewSportEquipment(){
      this.$router.push("/viewsportequipment");
    },
      clear(){
           this.sportEquipment.employee= null;
           this.sportEquipment.typeEquipment = null;
           this.sportEquipment.equipment = null;
           this.sportEquipment.typestatus = null;
           this.sportEquipment.number = null;
            
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
      this.gettypeEquipment();
      this.getstatus();
     }
   
  },
  mounted() {
    this.getemployee(),
    this.gettypeEquipment()
    this.getstatus()

    }
};
</script>
