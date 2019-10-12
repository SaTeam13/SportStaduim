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
        <h1 class="display-1 font-weight-bold mb-3">ลงทะเบียนพนักงาน</h1>
      </v-flex>
    </v-layout>

     <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
            <v-col cols="10">
              <v-text-field
                v-model="employee.nameInfo"
                :rules="[(v) => !!v || 'Item is required']"
                label="ชื่อพนักงาน"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="เพศ"
                  v-model="employee.genderid"
                  :items="gender"
                  item-text="sex"
                  item-value="genderid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.idcard"
                :rules="[(v) => !!v || 'Item is required']"
                label="เลขบัตรประชาชน"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.address"
                :rules="[(v) => !!v || 'Item is required']"
                label="ที่อยู่"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="จังหวัด"
                  v-model="employee.provinceid"
                  :items="province"
                  item-text="provincename"
                  item-value="provinceid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.phone"
                :rules="[(v) => !!v || 'Item is required']"
                label="โทร."
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="ตำแหน่ง"
                  v-model="employee.positionid"
                  :items="position"
                  item-text="positionlevel"
                  item-value="positionid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="บัญชีธนาคาร"
                  v-model="employee.bankid"
                  :items="bank"
                  item-text="bankname"
                  item-value="bankid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.banknum"
                :rules="[(v) => !!v || 'Item is required']"
                label="เลขที่บัญชี"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.user"
                :rules="[(v) => !!v || 'Item is required']"
                label="Username"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.pass"
                :rules="[(v) => !!v || 'Item is required']"
                label="Password"
                required
              ></v-text-field>
            </v-col>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn-toggle group >
                  <v-btn @click="saveEmployee" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                  <v-btn  @click="cancel">ยกเลิก</v-btn>
                  <v-btn @click="viewEmployee" color="#000000" style="color:#FFFFFF">ดูบันทึก</v-btn>
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
  name: "employee",
  data() {
    return {
      employee: {
        nameInfo: null,
        genderid: null,
        idcard: null,
        address: null,
        provinceid: null,
        phone: null,
        positionid: null,
        bankid: null,
        banknum: null,
        user: null,
        pass: null
      },
      gender:null,
      province:null,
      position:null,
      bank:null,
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
    // ดึงข้อมูล Position ใส่ combobox
    getposition() {
      http
        .get("/position")
        .then(response => {
          this.position = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Bank ใส่ combobox
    getbank() {
      http
        .get("/bank")
        .then(response => {
          this.bank = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // function เมื่อกดปุ่ม submit
    saveEmployee() {
      http
        .post(
          "/employee/" +
            this.employee.nameInfo +
            "/" +
            this.employee.genderid +
            "/" +
            this.employee.idcard +
            "/" +
            this.employee.address +
            "/" +
            this.employee.provinceid +
            "/" +
            this.employee.phone +
            "/" +
            this.employee.positionid +
            "/" +
            this.employee.bankid +
            "/" +
            this.employee.banknum +
            "/" +
            this.employee.user +
            "/" +
            this.employee.pass,
          this.employee
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
          this.$router.push("/viewemployee");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
     viewEmployee(){
      this.$router.push("/viewemployee");
    },
    cancel() {
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
      this.getposition();
      this.getbank();
    }
    /* eslint-enable no-console */
  },
  mounted() {
       this.getgender();
       this.getprovince();
       this.getposition();
       this.getbank();
  }
};
</script>
