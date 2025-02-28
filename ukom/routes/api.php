<?php

use App\Http\Controllers\LayananController;
use App\Http\Controllers\PelangganController;
use App\Http\Controllers\OrderController;
use App\Http\Controllers\PembayaranController;
use App\Http\Controllers\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::apiResource('/layanan', LayananController::class);
Route::apiResource('/pelanggan', PelangganController::class);
Route::apiResource('/order', OrderController::class);
Route::apiResource('/user', UserController::class);
Route::apiResource('/pembayaran', PembayaranController::class);
Route::post('/register', [UserController::class, 'store']);
Route::post('/login', [UserController::class, 'login']);

