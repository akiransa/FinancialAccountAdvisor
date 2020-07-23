import React, { Component } from 'react'
import { Bar } from 'react-chartjs-2'

const barData = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
        {
            label: 'My First dataset',
            backgroundColor: 'purple',
            borderColor: 'white',
            borderWidth: 1,
            hoverBackgroundColor: 'black',
            hoverBorderColor: 'blue',
            data: [10, 25, 40, 75, 9, 55, 40],
        },
    ],
}

class BarExample extends Component {
    render() {
        return (
            <div className="bar" height="15">
                Horizontal Bar Graph
                <Bar data={barData} width={100} height={15}></Bar>
            </div>
        )
    }
}

export default BarExample
