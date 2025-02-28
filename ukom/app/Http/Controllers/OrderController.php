<?php

namespace App\Http\Controllers;

use App\Models\layanan;
use App\Models\pelanggan;
use App\Models\order;
use Illuminate\Http\Request;

class OrderController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        
        $orders = Order::with('pelanggan', 'layanan')->get();

        return response()->json([
            'success' => true,
            'message' => 'Daftar Semua Order',
            'data' => $orders
        ], 200);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $request->validate([
            'pelanggan_id' => 'required|exists:pelanggan,id',
            'layanan_id'   => 'required|exists:layanan,id',
            'tanggal'      => 'required|date',
        ]);

        // Ambil harga layanan dari database
        $layanan = Layanan::find($request->layanan_id);
        if (!$layanan) {
            return response()->json([
                'success' => false,
                'message' => 'Layanan tidak ditemukan'
            ], 404);
        }

        $order = Order::create([
            'pelanggan_id' => $request->pelanggan_id,
            'layanan_id'   => $request->layanan_id,
            'total_harga'  => $layanan->harga, // Ambil harga dari layanan
            'tanggal'      => $request->tanggal, // Simpan tanggal dari request
            'status'       => 0, // Default status: pending
        ]);

        return response()->json([
            'success' => true,
            'message' => 'Order berhasil dibuat',
            'data' => $order
        ], 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $order = Order::find($id);
        if (!$order) {
            return response()->json(['message' => 'Order tidak ditemukan'], 404);
        }

        return response()->json($order);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $order = Order::find($id);
        if (!$order) {
            return response()->json(['message' => 'Order tidak ditemukan'], 404);
        }

        $order->update($request->only(['tanggal']));

        return response()->json($order);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $order = Order::find($id);
        if (!$order) {
            return response()->json(['message' => 'Order tidak ditemukan'], 404);
        }

        $order->delete();   

        return response()->json(['message' => 'Order berhasil dihapus']);
    }
}
