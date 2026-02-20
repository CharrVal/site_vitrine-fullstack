import { ProductImage } from "./product-image";

export interface Product {

  id: number;
  name: string;
  description: string;
  price: number;

  categoryId?: number;
  categoryName?: string;

  images: ProductImage[];
}