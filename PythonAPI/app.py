# -*- coding: utf-8 -*-
"""
Created on Mon Jul 20 20:21:25 2020

@author: Geetha
"""
import json
from flask import Flask, render_template, Response,jsonify,request
import matplotlib.pyplot as plt
from matplotlib.figure import Figure
import io
from matplotlib.backends.backend_agg import FigureCanvasAgg as FigureCanvas
#from flask_restful import Resource,Api

app=Flask(__name__)#gives file a unique name

Trans_Category=[]
xs=[]
ys=[]
@app.route('/trns/category',methods=['POST'])
def add_Trns():
    request_data=request.get_json()
    for  i in len(0,range(request_data['Data'].len)):
       for key,value in request_data[i].items():
           if key=='category':
               xs.append(value)
           elif key =='Amount':
               ys.append(value['Amount'])
    new_Trns={'Category':request_data['name']}
    Trans_Category.append(new_Trns)
    fig = create_figure()
    output=io.BytesIO()
    FigureCanvas(fig).print_png(output)
    return Response(output.getvalue(),mimetype='image/png')
    


@app.route('/graph')
def show_index():
    xs=[]
    ys=[]
    with open('C:/Geetha/APIHackathon/CodeHackathon/Trns.json') as f:
        request_data = json.load(f)
    for  entry in request_data['Data']:
      # print(entry)
       for key,value in entry.items():
           if key=='category':
               xs.append(value)
           elif key =='Amount':
               ys.append(value['Amount'])
    print(xs)
    print(ys)
    fig = create_figure(xs,ys)
    output=io.BytesIO()
    FigureCanvas(fig).print_png(output)
    return Response(output.getvalue(),mimetype='image/png')

def create_figure(xs,ys):
    fig=Figure()
    axis = fig.add_subplot(111)
     axis.bar(xs,ys)
    return fig

app.run(port=5000)#port