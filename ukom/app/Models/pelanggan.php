<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class pelanggan extends Model
{
    /** @use HasFactory<\Database\Factories\PelangganFactory> */
    use HasFactory;
    protected $table = 'pelanggan'; 

    protected $fillable = [
        'nama', 'alamat', 'no_hp'
    ];

    // Relasi dengan orders
    public function order()
    {
        return $this->hasMany(Order::class, 'pelanggan_id');
    }
}
