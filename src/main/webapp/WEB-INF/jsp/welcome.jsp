<!DOCTYPE HTML>
<html>
<head>
    <script>
        window.onload = function () {

            var dataPoints = [];

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Performance metrics"
                },
                axisY: {
                    title: "Units",
                    titleFontSize: 24
                },
                data: [{
                    type: "line",
                    yValueFormatString: "#,### Units",
                    dataPoints: dataPoints
                }]
            });

            function addData(data) {

                $.each(data, function (key, val) {
                    console.log("Key : " + key + ", Value : " + val);
                });
                var j = 0;
                for (var i = 0; i < 10; i++) {
                    j++;
                    dataPoints.push({
                        x: j,
                        y: 1
                    });
                }
                chart.render();
            }

            $.getJSON("test.json", addData);

        }
    </script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>