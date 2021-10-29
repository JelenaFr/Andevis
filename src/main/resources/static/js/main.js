//
// function getIndex(list, id) {
//   for (var i = 0; i<list.length; i++){
//     if(list[i].id ===id){
//       return i;
//     }
//   }
//
//   return -1;
// }
//
//
// var currencyRateApi = Vue.resource('/currencyRate{/id}');
//
// Vue.component('currencyRate-form', {
//   props: ['currencyRates', 'currencyRateAttr'],
//   data: function () {
//     return {
//       currencyRate: {currency:{code: '', rate: ''}, base: '', date: ''}
//     }
//   },
//   watch: {
//     currencyRateAttr: function (newVal, oldVal) {
//       this.currencyRate = JSON.parse(JSON.stringify(newVal));
//     }
//   },
//   template:
//     '<div>' +
//     '<div><input type="code" placeholder="Write code" v-model="currencyRate.currency.code"  /></div>' +
//     '<div><input type="rate" placeholder="Write rate" v-model="currencyRate.currency.rate"  /></div>' +
//     '<input type="button" value="Save" @click="save" />' +
//     '</div>',
//   methods: {
//     save: function () {
//       if (this.currencyRate.id) {
//         currencyRateApi.update({id: this.currencyRate.id}, this.currencyRate)
//           .then(result => result.json()
//             .then(data =>{
//               var index = getIndex(this.currencyRates, data.id);
//               this.currencyRates.splice(index, 1, data);
//             })
//           );
//       } else {
//         currencyRateApi.save({}, this.currencyRate).then(result =>
//           result.json().then(data => {
//             this.currencyRates.push(data);
//           })
//         );
//       }
//       this.currencyRate = {currency:{code: '', rate: ''}, base: '', date: ''};
//     }
//   }
// });
//
//
// Vue.component('currencyRate-row', {
//   props: ['currencyRate', 'editMethod',"currencyRates" ],
//   template: '<div>' +
//     '<i>({{" " + currencyRate.id + " " }})</i>{{" " +  currencyRate.currency.code }}{{" " +  currencyRate.currency.rate }}{{ " " + currencyRate.base }}{{ " " + currencyRate.date }}' +
//     '<span style="position: absolute; right: 0">' +
//     '<input type = "button" value="Edit" @click="edit"/>' +
//     '<input type = "button" value="Delete" @click="del"/>' +
//     '</span>' +
//     '</div>',
//   methods: {
//     edit: function () {
//       this.editMethod(this.currencyRate)
//
//     },
//     del: function () {
//       currencyRateApi.remove({id: this.currencyRate.id}).then(result =>{
//         if (result.ok){
//           this.currencyRates.splice(this.currencyRates.indexOf(this.currencyRate), 1)
//         }
//       })
//     }
//   }
// });
//
//
// Vue.component('currencyRates-list', {
//   props: ['currencyRates'],
//   data: function () {
//     return {
//       currencyRate: null
//     }
//   },
//   template: '<div style="position: relative; width: 500px;">' +
//     '<currencyRate-form :currencyRates="currencyRates" :currencyRateAttr="currencyRate" />' +
//     '<currencyRate-row v-for="currencyRate in currencyRates" :key= "currencyRate.id" :currencyRate="currencyRate" :editMethod="editMethod" :currencyRates="currencyRates"/>' +
//     '</div>',
//
//   methods: {
//     editMethod: function (currencyRate) {
//       this.currencyRate = currencyRate;
//
//     }
//   }
// });
//
//
//
// var app = new Vue({
//   el: '#app',
//   template: '<currencyRates-list :currencyRates="currencyRates"/>',
//   data: {
//     currencyRates: []
//   },
//   created: function () {
//     currencyRateApi.get().then(result =>
//       result.json().then(data =>
//         data.forEach(currencyRate => this.currencyRates.push(currencyRate))
//       )
//     )
//   },
// });
