const monthNames = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const weekName = [
  "Sunday",
  "Monday",
  "Tuesday",
  "Wednesday",
  "Thursday",
  "Friday",
  "Saturday",
];

const d = new Date();
const tomorrow = new Date();
tomorrow.setDate(tomorrow.getDate() + 1);
let startDate = d;
let endDate;

function changeStartDate(event) {
  let d = new Date(event.target.value);
  let dYesterday = new Date();
  dYesterday.setDate(tomorrow.getDate() - 2);

  if (Date.parse(d) < Date.parse(dYesterday)) {
    alert("CHOSE A DATE AFTER TODAY'S DATE");
  } else {
    if (Date.parse(endDate) < Date.parse(d)) {
      alert("CHOSE A CHECK-OUT DATE AFTER THE CHECK-IN ");
    } else {
      startDate = d;
      $(".today-date").html(
        `<h4 class="fs-6 text-blue">` +
          weekName[d.getDay()] +
          `</h4>
        <p class="fs-3">` +
          d.getDate() +
          monthNames[d.getMonth()] +
          `</p>`
      );
    }
  }
}

function changeEndDate(event) {
  let d = new Date(event.target.value);

  if (Date.parse(d) < Date.parse(startDate)) {
    console.log(d, " a  ", startDate);
    alert("CHOSE A DATE AFTER TODAY'S DATE");
  } else {
    endDate = d;
    $(".tomorrow-date").html(
      `<h4 class="fs-6 text-blue">` +
        weekName[d.getDay()] +
        `</h4>
      <p class="fs-3">` +
        d.getDate() +
        monthNames[d.getMonth()] +
        `</p>`
    );
  }
}

function setBaseDates() {
  $(".today-date").append(
    `<h4 class="fs-6 text-blue">` +
      weekName[d.getDay()] +
      `</h4>
    <p class="fs-3">` +
      d.getDate() +
      monthNames[d.getMonth()] +
      `</p>`
  );

  $(".tomorrow-date").append(
    `<h4 class="fs-6 text-blue">` +
      weekName[tomorrow.getDay()] +
      `</h4>
      <p class="fs-3">` +
      tomorrow.getDate() +
      monthNames[tomorrow.getMonth()] +
      `</p>`
  );
}

$("#start-date").click(function () {
  var input = document.querySelector("#start-date-picker");
  input.focus();
  input.click();

  console.log("cliked");
});

$(document).ready(function () {
  setBaseDates();
});
