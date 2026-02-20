import { ProductImage } from "./product-image";

export interface ProductRequest {
    name: string;
    description: string;
    price: number;
    categoryId: number;
    images?: ProductImage[];
}
