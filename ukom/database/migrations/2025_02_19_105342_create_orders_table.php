<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up()
{
    Schema::create('order', function (Blueprint $table) {
        $table->id();
        $table->foreignId('pelanggan_id')->references('id')->on('pelanggan')->onDelete('cascade');
        $table->foreignId('layanan_id')->references('id')->on('layanan')->onDelete('cascade');
        $table->smallInteger('status'); 
        $table->date('tanggal');
        $table->decimal('total_harga');
        $table->timestamps();
    });
}


    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('order');
    }
};
