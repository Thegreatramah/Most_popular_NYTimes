package com.ny.nytimesapp.model

import com.ny.nytimesapp.model.Result

data class ResultResponse(val status: String, val copyright: String, val num_results: String, val results: List<Result>)