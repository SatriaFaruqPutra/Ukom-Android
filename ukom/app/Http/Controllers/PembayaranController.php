<?php

namespace App\Http\Controllers;

use App\Models\order;
use App\Models\Pembayaran;
use Illuminate\Http\Request;

class PembayaranController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $pembayaran = Pembayaran::with('order')->get();

    return response()->json([
        'success' => true,
        'message' => 'Data pembayaran berhasil diambil',
        'data'    => $pembayaran
    ]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create($order_id)
    {
        // Ambil data order berdasarkan ID
        $order = Order::find($order_id);

        if (!$order) {
            return response()->json([
                'success' => false,
                'message' => 'Order tidak ditemukan'
            ], 404);
        }

        return response()->json([
            'success' => true,
            'message' => 'Data order ditemukan',
            'data' => [
                'order_id'     => $order->id,
                'total_harga'  => $order->total_harga,
            ]
        ]);
    }

    public function store(Request $request)
    {
        $request->validate([
            'order_id' => 'required|exists:order,id',
            'bayar'    => 'required|numeric|min:0',
            'kembali'  => 'required|numeric|min:0',
        ]);

        // Simpan data pembayaran
        $pembayaran = Pembayaran::create([
            'order_id' => $request->order_id,
            'jumlah'   => order::find($request->order_id)->total_harga, // Ambil total harga dari order
            'bayar'    => $request->bayar,
            'kembali'  => $request->kembali,
        ]);

        return response()->json([
            'success' => true,
            'message' => 'Pembayaran berhasil disimpan',
            'data' => $pembayaran
        ]);
    }

    /**
     * Display the specified resource.
     */
    public function show(Pembayaran $pembayaran)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Pembayaran $pembayaran)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Pembayaran $pembayaran)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Pembayaran $pembayaran)
    {
        //
    }
}
