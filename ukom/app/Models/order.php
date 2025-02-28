<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class order extends Model
{
    /** @use HasFactory<\Database\Factories\OrderFactory> */
    use HasFactory;
    protected $table = 'order';

    protected $fillable = [
        'pelanggan_id', 
        'layanan_id', 
        'status', 
        'tanggal', 
        'total_harga', 
    ];

    // Relasi ke pelanggan
    public function pelanggan()
    {
        return $this->belongsTo(Pelanggan::class, 'pelanggan_id');
    }

    // Relasi ke layanan
    public function layanan()
    {
        return $this->belongsTo(Layanan::class, 'layanan_id');
    }

    public function pembayaran()
    {
        return $this->hasOne(Pembayaran::class, 'order_id');
    }
}
