/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
const webpack = require("webpack");
const path = require("path");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

var config = {
	mode: 'development',
    entry: './src/index.ts',
    output: {
		path: path.resolve(__dirname, "./dist"),
		filename: "bundle.js"
    },
    devtool: 'inline-source-map',
    resolve: {
        // Add `.ts` and `.tsx` as a resolvable extension.
    	extensions: [ '.webpack.js', '.web.js', '.ts', '.tsx', '.js', '.json']
    },
    module: {
        rules: [
            // all files with a '.ts' or '.tsx' extension will be handled by 'ts-loader'
            { test: /\.tsx?$/, use: ["ts-loader"], exclude: /node_modules/ }
          ]
//        loaders: [
//            // all files with a `.ts` or `.tsx` extension will be handled by `ts-loader`
//            {test: /\.tsx?$/, loader: 'ts-loader'}
//        ]
    },
    plugins: [
    	//new CleanWebpackPlugin(),
        new CopyPlugin({
          patterns: [
            { from: 'assets', to: '' },
          ],
        }),
        new HtmlWebpackPlugin({
            template: 'src/index.html'
        }),
      ],
};
module.exports = config;
