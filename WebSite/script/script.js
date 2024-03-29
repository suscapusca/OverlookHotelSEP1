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

const today = new Date();
const tomorrow = new Date();
tomorrow.setDate(tomorrow.getDate() + 1);

const UserInput = {
  checkIn: today,
  checkOut: tomorrow,
};

var date2 = {
  checkIn: today,
  checkOut: tomorrow,
};

const Rooms = {};

function changeStartDate(event) {
  let d = new Date(event.target.value);
  let dYesterday = new Date();
  dYesterday.setDate(tomorrow.getDate() - 2);

  if (Date.parse(d) < Date.parse(dYesterday)) {
    alert("CHOSE A DATE AFTER TODAY'S DATE");
  } else {
    if (Date.parse(UserInput.checkOut) < Date.parse(d)) {
      alert("CHOSE A CHECK-OUT DATE AFTER THE CHECK-IN ");
    } else {
      UserInput.checkIn = d;
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

  if (Date.parse(d) < Date.parse(UserInput.checkIn)) {
    console.log(d, " a  ", UserInput.checkIn);
    alert("CHOSE A DATE AFTER TODAY'S DATE");
  } else {
    UserInput.checkOut = d;
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
      weekName[today.getDay()] +
      `</h4>
    <p class="fs-3">` +
      today.getDate() +
      monthNames[today.getMonth()] +
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
  getXmlFile();
});

function getBooking() {
  // path /HotelOverLookJavaFX/src/main/resources/student.xml
  $.get(
    "/HotelOverLookJavaFX/src/main/resources/student.xml",
    function (xml, status) {
      var document = $(xml);
      guest = document.find("guest").each(function () {
        console.log($(this).find("name").text());
      });
    }
  );
}

//Check if 2 dates are overlapping (date must be an object with check-in as start date and check-out as ending date)
function isOverlapping(date1, date2) {
  date1Start = date1.checkIn;
  date1End = date1.checkOut;

  date2Start = date2.checkIn;
  date2End = date2.checkOut;

  if (!(date1End >= date2Start && date1Start <= date2End)) {
    console.log("not overlapping");
    return false;
  } else console.log("Overlapping");
  return true;
}

function checkBooking() {
  isOverlapping(UserInput, date2);
}

function getXmlFile() {
  $.get(
    "/HotelOverLookJavaFX/src/main/resources/student.xml",
    function (xml, status) {
      let myXml = $(xml);
      let room = myXml.find("room");
      console.log(room.text());
      room.each(function () {
        let year = $(this).find("year").text();
        let month = $(this).find("month").text();
        let day = $(this).find("day").text();

        let d = new Date(year + "-" + month + "-" + day);
        console.log(d);
        console.log(year + "-" + month + "-" + day);
      });
    }
  );
}
