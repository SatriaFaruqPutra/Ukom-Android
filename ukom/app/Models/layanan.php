<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class layanan extends Model
{
    /** @use HasFactory<\Database\Factories\LayananFactory> */
    use HasFactory;
    protected $table = 'layanan';

    protected $fillable = [
        'nama_layanan', 
        'jenis_kendaraan', 
        'deskripsi', 
        'harga'
    ];

    // Relasi dengan orders
    public function order()
    {
        return $this->hasMany(Order::class, 'layanan_id');
    }
}
