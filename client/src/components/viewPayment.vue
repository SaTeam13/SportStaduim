<template>
  <v-container>
    <v-app-bar style="background: white;" app>
      <v-toolbar-title class="headline text-uppercase">
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
            <h1 class="display-1 font-weight-bold mb-3">ข้อมูลการชำระเงิน</h1>
          </v-flex>
        </v-layout>
        <v-row justify="center">
          <v-col cols="5">
            <v-text-field
              v-model="search"
              label="ค้นหา"
              prepend-icon="mdi mdi-file-find"
              outlined
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-data-table
              :headers="headers"
              :items="items"
              :items-per-page="5"
              class="elevation-1"
              :search="search"
            ></v-data-table>
          </v-col>

          <v-col cols="3">
            <v-text-field
              outlined
              label="ต้องการลบ Payment ID: "
              prepend-icon="mdi mdi-delete-forever"
              v-model="payment.paymentId"
            ></v-text-field>
            <p v-if="paymentCheck != ''">
              ID Payment ที่ต้องการลบ : {{paymentId}}
              <v-btn class @click="deletePayments" color="#D50000" style="color:#FFFFFF">ลบ</v-btn>
            </p>
          </v-col>
          <v-col cols="2">
            <div class>
              <v-btn
                @click="findPayments"
                depressed
                large
                color="#282FCF"
                style="color:#FFFFFF;"
              >ยืนยัน</v-btn>
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
  name: "viewPayment",
  data() {
    return {
      search: "",
      headers: [
        {
          text: "Payment ID",
          align: "center",
          sortable: false,
          value: "paymentid"
        },
        { text: "ชื่อผู้ใช้บริการ", value: "customerid.customername" },
        {
          text: "ID ผู้ใช้บริการ",
          value: "customerid.customerid",
          align: "center"
        },
        { text: "ประเภทลูกค้า", value: "customertypeid.customertypename" },
        { text: "วันที่ทำรายการ", value: "payDate" },
        { text: "ช่วงระยะเวลา", value: "timerangeid.timerangename" },
        { text: "ทำรายการโดย", value: "employeeid.employeename" }
      ],
      items: [],
      payment: {
        paymentId: null
      },
      valid: false,
      paymentCheck: false,
      paymentId: null
    };
  },
  methods: {
    /* eslint-disable no-console */
    getPayments() {
      http
        .get("/paymentmember")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    findPayments() {
      http
        .get("/paymentmember/" + this.payment.paymentId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.paymentId = response.data.paymentid;
            this.paymentCheck = response.status;
          } else {
            this.clear();
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deletePayments() {
      http
        .delete("/paymentmember/" + this.payment.paymentId)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          alert("ทำการลบรายการชำระเงินเรียบร้อย");
          location.reload();
        })
        .catch(e => {
          console.log(e);
        });
    },
    pushReserve() {
      this.$router.push("/reservation");
    },
    pushBorrow() {
      this.$router.push("/borrow");
    },
    pushCustomer() {
      this.$router.push("/customer");
    },
    pushEmployee() {
      this.$router.push("/employee");
    },
    pushPayment() {
      this.$router.push("/paymentmember");
    },
    pushSportEquipment() {
      this.$router.push("/sportequipment");
    },
    refreshList() {
      this.getPayments();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getPayments();
  }
};
</script>
