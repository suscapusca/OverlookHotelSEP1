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

$("#today-date").append(
  `<h4 class="fs-6 text-blue">` +
    weekName[d.getDay()] +
    `</h4>
  <p class="fs-3">` +
    d.getDate() +
    monthNames[d.getMonth()] +
    `</p>`
);

$("#tomorrow-date").append(
  `<h4 class="fs-6 text-blue">` +
    weekName[tomorrow.getDay()] +
    `</h4>
    <p class="fs-3">` +
    tomorrow.getDate() +
    monthNames[tomorrow.getMonth()] +
    `</p>`
);

$(document).ready(function () {});
