<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pembayaran extends Model
{
    /** @use HasFactory<\Database\Factories\PembayaranFactory> */
    use HasFactory;

    protected $table = 'pembayaran';

    protected $fillable = [
        'order_id',
        'jumlah',
        'bayar',
        'kembali',
    ];

    public function order()
    {
        return $this->belongsTo(Order::class);
    }
}
