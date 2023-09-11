<template>
    <div>
        <Line
        v-if="elevationJson"
        id="my-chart-id"
        :options="chartOptions"
        :data="chartData"
        />
    </div>
  </template>
  
  <script>
  import { Line } from 'vue-chartjs'
  import { get } from '@/modules/AuthorisedRequest.js';
  import { Chart as ChartJS, Tooltip, LineElement, LineController, CategoryScale, LinearScale, PointElement, Title, Filler } from 'chart.js'
  
  ChartJS.register(Tooltip, LineElement, LineController, CategoryScale, LinearScale, PointElement, Title, Filler)
  
  export default {
    name: 'ElevationChart',
    components: { Line },
    props: {
        routeId: String
    },
    data() {
      return {
        elevationJson: null,
        chartData: {
            labels: [], 
            datasets: [{
                data: [], 
                fill: true,
                borderColor: '#51e3a1',
                backgroundColor: '#3ba274',
                tension: 0.2,
                pointRadius: 0,
                spanGaps: true
            }]
        },
        chartOptions: {
            animation: true,
            responsive: true,
            maintainAspectRatio: false,
            interaction: { intersect: false, mode: 'index' },
            tooltip: { position: 'nearest' },
            scales: {
                x: { type: 'linear', title: { display: true, text: 'Distance (km)'} },
                y: { type: 'linear', beginAtZero: false, title: { display: true, text: 'Elevation (m)'} }
            },
            plugins: {
                title: { align: "start", display: true, text: "Elevation Profile" },
                legend: { display: false },
                tooltip: {
                    displayColors: false,
                    callbacks: {
                        title: (tooltipItems) => {
                            return "Distance: " + tooltipItems[0].label + 'km'
                        },
                        label: (tooltipItem) => {
                            return "Elevation: " + tooltipItem.raw + 'm'
                        },
                    }
                }
            }
        }
      }
    },
    created() {
      const routeUrl = process.env.VUE_APP_ROUTE_SERVER_URI + '/elevation/'+this.routeId;
      get(routeUrl)
        .then((response) => { 
            this.elevationJson = response.data; 
            this.chartData.labels = [];
            this.chartData.datasets[0].data = [];
            if (this.elevationJson != null && this.elevationJson.points != null) {
                this.elevationJson.points.forEach((point) => {
                    this.chartData.labels.push(Math.round(point.distance / 10)/100);
                    this.chartData.datasets[0].data.push(Math.round(point.elevation));
                });
            }
        });
    },
  }
  </script>
  