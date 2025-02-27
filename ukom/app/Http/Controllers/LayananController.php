<?php

namespace App\Http\Controllers;

use App\Models\layanan;
use Illuminate\Http\Request;

class LayananController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return response()->json(layanan::all());
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
        $data = $request->validate([
            'nama_layanan'      => 'required|string',
            'jenis_kendaraan'   => 'required|string',
            'deskripsi'         => 'required|string',
            'harga'             => 'required|',
        ]);

        $newdata = Layanan::create($data);

        return response()->json($newdata, 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $layanan = Layanan::find($id);
        if (!$layanan) {
            return response()->json(['message' => 'Layanan tidak ditemukan'], 404);
        }

        return response()->json($layanan);
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
        $layanan = Layanan::find($id);
        if (!$layanan) {
            return response()->json(['message' => 'Layanan tidak ditemukan'], 404);
        }

        $data = $request->validate([
            'nama_layanan'      => 'required|string',
            'jenis_kendaraan'   => 'required|string',
            'deskripsi'         => 'required|string',
            'harga'             => 'required',
        ]);

        $layanan->update($data);

        return response()->json($layanan);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $layanan = Layanan::find($id);
        if (!$layanan) {
            return response()->json(['message' => 'Layanan tidak ditemukan'], 404);
        }

        $layanan->delete();

        return response()->json(['message' => 'Layanan berhasil dihapus']);
    }
}
